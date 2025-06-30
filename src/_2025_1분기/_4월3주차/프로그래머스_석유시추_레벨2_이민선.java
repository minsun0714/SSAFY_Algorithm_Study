package _4월3주차;

import java.util.*;

// bfs
public class 프로그래머스_석유시추_레벨2_이민선 {
    static int n, m;
    static Map<Integer, Integer> map = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;

        // 각 석유 구간에 번호 부여
        // 번호에 해당하는 넓이 저장
        // 각 열을 탐색하면서 만나는 번호를 set에 저장
        int count = 1;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (land[i][j] == 1) {
                    bfs(i, j, land, ++count);
                }
            }

        }

        int answer = 0;

        for (int j=0;j<m;j++){
            Set<Integer> set = new HashSet<>();
            for (int i=0;i<n;i++){
                if (map.get(land[i][j]) == null) continue;
                set.add(land[i][j]);
            }

            int sum = 0;
            for (int key:set){
                sum += map.get(key);
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    private static void bfs(int x, int y, int[][] land, int key){
        map.put(key, 1);
        land[x][y] = key;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()){
            int[] cur = q.poll();
            x = cur[0];
            y = cur[1];

            for (int i=0;i<4;i++){
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (land[nx][ny] == 0 || land[nx][ny] == key) continue;

                land[nx][ny] = key;

                map.put(key, map.get(key) + 1);

                q.offer(new int[]{nx, ny});
            }
        }
    }
}
