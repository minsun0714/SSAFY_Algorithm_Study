// simple sort
// [ 701 ms | 56 mb ]
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(input.readLine());
        int[][] originalArr = new int[4][N];
        int[][] sortedArr = new int[4][N];
        Arrays.fill(sortedArr[3], 0);

        for(int round=0; round<3; round++){
            tokens = new StringTokenizer(input.readLine());
            for(int i=0; i < N; i++){
                int score = Integer.parseInt(tokens.nextToken());
                originalArr[round][i] += score;
                originalArr[3][i] += score;

                sortedArr[round][i] = score;
                sortedArr[3][i] += score;    
            }
        }

        for(int i=0; i < 4; i++){
            Arrays.sort(sortedArr[i]);
        }

        Map<Integer, Integer> [] score_rankMapArr = new HashMap[4];
        for(int r=0; r < 4; r++){
            score_rankMapArr[r] = new HashMap();
        }

        for(int r=0; r < 4; r++){
            int coin = sortedArr[r][0];
            for(int i=0; i < N; i++){
                if(coin != sortedArr[r][i]){
                    score_rankMapArr[r].put(coin, N-i+1);
                    coin = sortedArr[r][i];
                }
            }
            score_rankMapArr[r].put(sortedArr[r][N-1], 1);
        }
        
        for(int r=0; r < 4; r++){     
            for(int i=0; i < N; i++){ 
                int rankKey = originalArr[r][i];
                output.append(score_rankMapArr[r].get(rankKey)).append(" ");
            }
            output.append("\n");

        }
        System.out.println(output);

    }
}
