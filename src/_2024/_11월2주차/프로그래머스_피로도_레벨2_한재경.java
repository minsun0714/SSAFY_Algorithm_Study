//순열 + 브루트포스
class 프로그래머스_피로도_레벨2_한재경 {
    static int maxCnt = 0; //방문가능 최대던전수
    static int sk;
    static int[][] ds;
    public void permutations(int depth, int n, int[] now, int[] visited) { //던전 방문 순서 인덱스 순열 생성
        if (depth == n) {
            int nowk = sk;
            int cnt = 0;
            for (int a : now) { //해당 경로로 방문
                int e = explore(a, nowk);
                if (e == -1) {
                    break;
                }
                cnt++;
                nowk = e;
            }
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                now[depth] = i;
                permutations(depth + 1, n, now, visited);
                now[depth] = 0;
                visited[i] = 0;
            }
        }
    }
    
    public int explore(int didx, int nowk) { //던전인덱스, 현재피로도
        if (nowk >= ds[didx][0]) { //탐험 가능
            nowk -= ds[didx][1];
        } else {
            return -1;
        }
        return nowk; //현재 피로도 반환
    }
    
    public int solution(int k, int[][] dungeons) { //k: 피로도
        sk = k;
        ds = dungeons;
        //dungeons: ["최소 필요 피로도", "소모 피로도"], ...
        permutations(0, dungeons.length, new int[dungeons.length], new int[dungeons.length]);
        
        return maxCnt;
    }
}
