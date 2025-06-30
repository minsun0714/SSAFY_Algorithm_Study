package _2월2주차;

class 프로그래머스_자물쇠와열쇠_레벨2_한재경 {
    static int n, m, hole;
    
    public boolean unlock(int[][] lock, int[][] key) {
        int cnt = 0;
        boolean isPossible = true;
        
        // key 대칭이동하며 맞춰보면서 브루트포스
        for (int i = -m + 1; i < n; i++) { //자물쇠
            for (int j = -m + 1; j < n; j++) {
                isPossible = true;
                cnt = 0;
                for (int x = 0; x < m; x++) { //열쇠
                    for (int y = 0; y < m; y++) {
                        if (i+x < 0 || i+x >= n || j+y < 0 || j+y >= n) {continue;}
                        if (lock[i+x][j+y] == 0) { //홀
                            if (key[x][y] == 1) {
                                cnt++;                            
                            } else { //불가
                                isPossible = false;
                                break;
                            }
                        } else { //벽
                            if (key[x][y] == 1) { //불가
                                isPossible = false;
                                break;
                            }
                        }
                    }
                    if (!isPossible) { break; }
                } 
                if (isPossible && cnt == hole) { return true;}
            }
        }
        return false;
    }
    //키 90도 회전
    public int[][] turn(int[][] key) {
        int[][] grid = new int[m][m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                grid[m - j - 1][i] = key[i][j];
            }
        }
        
        return grid;
    }
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        n = lock[0].length; //lock 크기
        m = key[0].length; //key 크기
        hole = 0; //구멍 개수
        
        for (int i = 0; i < n; i++) { //자물쇠 개수
            for (int j = 0; j < n; j++) {
                if (lock[i][j] == 0) {hole++;}
            }
        }
        
        boolean ans = false;
        for (int a = 0; a < 4; a++) {
            ans = unlock(lock, key);
            if (ans) {return true;}
            key = turn(key);
        }
        
        return ans;
    }
}
