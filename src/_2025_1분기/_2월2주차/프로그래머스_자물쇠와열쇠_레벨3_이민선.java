package _2월2주차;

public class 프로그래머스_자물쇠와열쇠_레벨2_이민선 {
    static int[][] board;
    static int m;
    static int n;
    public boolean solution(int[][] key, int[][] lock) {
        m = key.length;
        n = lock.length;
        board = new int[3 * n][3 * n];

        for (int i=n;i<2*n;i++){
            for (int j=n;j<2*n;j++){
                board[i][j] = lock[i - n][j - n];
            }
        }

        int[][] rotatedKey = key;
        for (int d=0;d<4;d++){
            rotatedKey = rotate(rotatedKey);

            for (int startX=0;startX<2*n;startX++){
                for (int startY=0;startY<2*n;startY++){
                    for (int x=startX;x<startX+m;x++){
                        for (int y =startY;y<startY+m;y++){
                            board[x][y] += rotatedKey[x - startX][y - startY];
                        }
                    }

                    int count = 0;
                    for (int i=n;i<2*n;i++){
                        for (int j=n;j<2*n;j++){
                            if (board[i][j] == 1) count++;
                        }
                    }

                    if (count == n * n) return true;

                    for (int x=startX;x<startX+m;x++){
                        for (int y =startY;y<startY+m;y++){
                            board[x][y] -= rotatedKey[x - startX][y - startY];
                        }
                    }
                }
            }
        }
        return false;
    }

    public int[][] rotate(int[][] key){
        int[][] rotated = new int[m][m];
        for (int i=0;i<m;i++){
            for (int j=0;j<m;j++){
                rotated[j][m - i - 1] = key[i][j];
            }
        }
        return rotated;
    }
}
