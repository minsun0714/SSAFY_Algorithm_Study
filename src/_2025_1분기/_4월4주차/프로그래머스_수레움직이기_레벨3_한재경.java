import java.util.*;

// BFS
class 프로그래머스_수레움직이기_레벨3_한재경 {
    private int n, m;
    private int redTarget, blueTarget;
    private final int[] dr = {-1, 1, 0, 0};
    private final int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;

        int redStart = -1, blueStart = -1;
        redTarget = blueTarget = -1;
        // 시작/도착 위치 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                switch (maze[i][j]) {
                    case 1: redStart   = i * m + j; break;
                    case 2: blueStart  = i * m + j; break;
                    case 3: redTarget  = i * m + j; break;
                    case 4: blueTarget = i * m + j; break;
                }
            }
        }

        // 초기 상태
        int initRedMask  = 1 << redStart;
        int initBlueMask = 1 << blueStart;
        boolean initRedDone  = (redStart == redTarget);
        boolean initBlueDone = (blueStart == blueTarget);
        State init = new State(redStart, blueStart, initRedMask, initBlueMask, initRedDone, initBlueDone);

        Queue<Node> q = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        q.offer(new Node(init, 0));
        visited.add(init);

        // BFS
        while (!q.isEmpty()) {
            Node curNode = q.poll();
            State cur = curNode.s;
            int turns = curNode.depth;

            // 둘 다 도착했으면 정답
            if (cur.redDone && cur.blueDone) {
                return turns;
            }

            // 빨간 수레 이동 방향 수
            int redDirCount  = cur.redDone  ? 1 : 4;
            int blueDirCount = cur.blueDone ? 1 : 4;

            for (int rd = 0; rd < redDirCount; rd++) {
                int newRedPos  = cur.redPos;
                int newRedMask = cur.redMask;
                boolean newRedDone = cur.redDone;

                // 빨간 수레가 아직 도착 전인 경우에만 이동 계산
                if (!cur.redDone) {
                    int rr = cur.redPos / m + dr[rd];
                    int rc = cur.redPos % m + dc[rd];
                    if (rr < 0 || rr >= n || rc < 0 || rc >= m) continue;
                    int cell = maze[rr][rc];
                    if (cell == 5) continue;              // 벽
                    int idx = rr * m + rc;
                    if ((cur.redMask & (1 << idx)) != 0) continue;  // 이미 방문
                    newRedPos = idx;
                    newRedMask |= (1 << idx);
                    if (idx == redTarget) newRedDone = true;
                }

                for (int bd = 0; bd < blueDirCount; bd++) {
                    int newBluePos  = cur.bluePos;
                    int newBlueMask = cur.blueMask;
                    boolean newBlueDone = cur.blueDone;

                    if (!cur.blueDone) {
                        int br = cur.bluePos / m + dr[bd];
                        int bc = cur.bluePos % m + dc[bd];
                        if (br < 0 || br >= n || bc < 0 || bc >= m) continue;
                        int cell = maze[br][bc];
                        if (cell == 5) continue;
                        int idx = br * m + bc;
                        if ((cur.blueMask & (1 << idx)) != 0) continue;
                        newBluePos = idx;
                        newBlueMask |= (1 << idx);
                        if (idx == blueTarget) newBlueDone = true;
                    }

                    // 같은 칸에 겹치는 이동 금지
                    if (newRedPos == newBluePos) continue;
                    // 자리 바꾸며 교차 이동 금지
                    if (newRedPos == cur.bluePos && newBluePos == cur.redPos) continue;

                    State next = new State(newRedPos, newBluePos,
                                           newRedMask, newBlueMask,
                                           newRedDone, newBlueDone);
                    if (visited.add(next)) {
                        q.offer(new Node(next, turns + 1));
                    }
                }
            }
        }

        // 풀 수 없으면 0
        return 0;
    }

    // 상태를 담는 클래스
    private static class State {
        int redPos, bluePos, redMask, blueMask;
        boolean redDone, blueDone;
        State(int rp, int bp, int rm, int bm, boolean rd, boolean bd) {
            redPos = rp; bluePos = bp;
            redMask = rm; blueMask = bm;
            redDone = rd; blueDone = bd;
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof State)) return false;
            State s = (State) o;
            return redPos == s.redPos && bluePos == s.bluePos
                && redMask == s.redMask && blueMask == s.blueMask
                && redDone == s.redDone && blueDone == s.blueDone;
        }
        @Override
        public int hashCode() {
            return Objects.hash(redPos, bluePos, redMask, blueMask, redDone, blueDone);
        }
    }

    // BFS 큐에 담길 래퍼
    private static class Node {
        State s;
        int depth;
        Node(State s, int d) { this.s = s; depth = d; }
    }
}
