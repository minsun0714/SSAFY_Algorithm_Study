import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//누적합
public class 백준_20002_사과나무_골드5_한재경_352ms {
    static int maxProfit = Integer.MIN_VALUE; //최대이익
    static int n;
    static int[][] grid;
    static int[][] sumGrid;

    static void sumSquare(int x2, int y2, int x1, int y1) { //사각형 합 계산 (x1,y1) ~ (x2,y2)
        int squareSum = sumGrid[x2][y2] - sumGrid[x1 - 1][y2] - sumGrid[x2][y1 - 1] + sumGrid[x1 - 1][y1 - 1];
        maxProfit = Math.max(maxProfit, squareSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); //n*n
        grid = new int[n + 1][n + 1]; //(1,1)부터 시작

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //누적합 grid 만들기
        sumGrid = new int[n + 1][n + 1]; //(1,1)부터 시작

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sumGrid[i][j] = grid[i][j] + sumGrid[i - 1][j] + sumGrid[i][j - 1] - sumGrid[i - 1][j - 1];
            }
        }

        for (int s = 0; s < n; s++) { //step
            for (int i = 1; i <= n - s; i++) {
                for (int j = 1; j <= n - s; j++) {
                    sumSquare(i + s, j + s, i, j); //(i, j)부터 (i+s, j+s)까지 정사각형
                }
            }
        }
        System.out.println(maxProfit);
    }
}
