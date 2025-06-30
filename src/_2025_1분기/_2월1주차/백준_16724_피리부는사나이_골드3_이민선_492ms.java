package _2월1주차;

import java.io.*;
import java.util.*;

    public class 백준_16724_피리부는사나이_골드3_이민선_492ms {
        static int n, m;
        static char[][] board;
        static Map<Character, int[]> dirs = new HashMap<>();
        static {
            dirs.put('U', new int[]{-1, 0});
            dirs.put('R', new int[]{0, 1});
            dirs.put('D', new int[]{1, 0});
            dirs.put('L', new int[]{0, -1});
        }
        static int[][] visited;
        static int[] parent;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new char[n][m];
            for (int i=0;i<n;i++){
                String input = br.readLine();
                for (int j=0;j<m;j++){
                    board[i][j] = input.charAt(j);
                }
            }
            visited = new int[n][m];
            parent = new int[n * m];
            Arrays.setAll(parent, (i) -> i);
            int count = 1;
            for (int i=0;i<n;i++){
                for (int j=0;j<m;j++){
                    if (visited[i][j] == 0){
                        visited[i][j] = count;
                        dfs(i, j, count);
                        count++;
                    }
                }
            }
            Set<Integer> set = new HashSet<>();
            for (int i=0;i<n;i++){
                for (int j=0;j<m;j++){
                    set.add(parent[visited[i][j]]);
                }
            }
            System.out.println(set.size());
        }

        public static int find(int x){
            if (parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public static void union(int a, int b){
            a = find(a);
            b = find(b);

            if (a < b){
                parent[b] = a;
            } else {
                parent[a] = b;
            }
        }

        public static void dfs(int x, int y, int count){
            if (x < 0 || y < 0 || x >= n || y >= m) return;

            int nx = x + dirs.get(board[x][y])[0];
            int ny = y + dirs.get(board[x][y])[1];
            if (visited[nx][ny] >= 1) {
                if (visited[nx][ny] != count)
                    union(visited[nx][ny], count);
                return;
            };
            visited[nx][ny] = count;

            dfs(nx, ny, count);

        }
    }