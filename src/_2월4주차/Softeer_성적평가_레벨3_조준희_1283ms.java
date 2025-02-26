package _2월4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Softeer_성적평가_레벨3_조준희_1283ms {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int [][][] scores = new int[4][N][2];
        Map<Integer, int[]> result = new HashMap<>();
        for(int i = 0; i<N; i++){
            result.put(i, new int[4]);
        }

        for(int i = 0; i<3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                int score = Integer.parseInt(st.nextToken());
                scores[i][j][0] = score;
                scores[i][j][1] = j;

                scores[3][j][0] += score;
                scores[3][j][1] = j;
            }
        }

        for(int i = 0; i<4; i++){
            int rank = 0;
            int tie = 0;
            Arrays.sort(scores[i], (int[] a1, int[] a2)-> a2[0]-a1[0]);

            for(int j = 0; j<N; j++){
                int[] rankArr = result.get(scores[i][j][1]);
                if(j>0 && scores[i][j][0]==scores[i][j-1][0]){
                    tie++;
                    rankArr[i]=rank;
                }
                else{
                    rank++;
                    rankArr[i]=rank+tie;
                    rank+=tie;
                    tie=0;


                }
            }
        }

        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(result.get(j)[i]);
                if (j < N - 1) sb.append(' ');
            }
            System.out.println(sb);
        }

    }
}
