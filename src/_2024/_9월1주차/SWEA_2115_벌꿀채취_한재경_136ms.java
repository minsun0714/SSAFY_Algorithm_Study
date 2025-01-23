import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//136ms, 조합
public class SWEA_2115_벌꿀채취_한재경_136ms {
    static int n;
    static int m;
    static int c;
    static int[][] grid;
    static int indMaxProfit;

    static int getHoney() {
        int maxProfit = 0;
        int fstProfit = 0;
        int secProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - m; j++) { //1번통이 [i,j]일 때 2번통이 [a,b]
                indMaxProfit = 0; //개별 꿀통 profit 초기화
                //m개중에 골라서 c이하 최댓값 찾는 조합
                selectHoneyCells(i, j, j, 0, 0);
                fstProfit = indMaxProfit;
                for (int a = i; a < n; a++) {
                    if (a == i) { //같은 행이면
                        for (int b = j + m; b <= n - m; b++) {
                            indMaxProfit = 0;
                            selectHoneyCells(a, b, b, 0, 0);
                            secProfit = indMaxProfit;
                            maxProfit = Math.max(maxProfit, fstProfit + secProfit);
                            secProfit = 0;
                        }
                    } else { //다른 행이면
                        for (int b = 0; b <= n - m; b++) {
                            indMaxProfit = 0;
                            selectHoneyCells(a, b, b, 0, 0);
                            secProfit += indMaxProfit;
                            maxProfit = Math.max(maxProfit, fstProfit + secProfit);
                            secProfit = 0;
                        }
                    }
                }
            }
        }
        return maxProfit;
    }

    static void selectHoneyCells(int x, int y, int start, int sum, int profit) { //시작점 기준 m개중에 골라서 c이하 최댓값 찾는 조합
        if (sum > c) {
            return;
        }
        indMaxProfit = Math.max(profit, indMaxProfit);
        for (int j = start; j < y + m; j++) {
            sum += grid[x][j]; //고르고
            profit += grid[x][j] * grid[x][j];
            selectHoneyCells(x, y, j + 1, sum, profit);
            sum -= grid[x][j]; //안 고른 경우 탐색
            profit -= grid[x][j] * grid[x][j];
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); //n*n배열
            m = Integer.parseInt(st.nextToken()); //가로 m칸 꿀통
            c = Integer.parseInt(st.nextToken()); //각 꿀통 한계치
            grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //가로 연속 m개 선택, 겹치기 불가, 개별 summation, 두칸중 한칸만 가능
            //각 꿀 제곱만큼 수익
            int result = getHoney();
            sb.append("#" + t + " " + result + "\n");
        }
        System.out.println(sb);
    }
}
