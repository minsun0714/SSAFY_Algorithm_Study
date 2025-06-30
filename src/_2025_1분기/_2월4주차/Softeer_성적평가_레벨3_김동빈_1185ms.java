import java.io.*;
import java.util.*;

// 1185ms, 단순 순회
public class Softeer_성적평가_레벨3_김동빈_1185ms {

    static class Info implements Comparable<Info> {
        int id;
        int score;

        public Info(int id, int score){
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Info o){
            return Integer.compare(this.score, o.score);
        }
        
    }
    
    static int n;
	static int[][] answerArray;
	
    public static void main(String[] args) throws Exception {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        answerArray = new int[3 + 1][n]; // 정답을 담을 배열
        Info[][] scores = new Info[3 + 1][n];

        int[] totalScore = new int[n]; // 마지막
        
        for (int i = 0; i < 3; ++i){
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < n; ++j) {
                scores[i][j] = new Info(j, Integer.parseInt(st.nextToken()));
                
                totalScore[j] += scores[i][j].score; // 최종 점수에 더하기
            }
        }

        // 마지막 행
        for (int i = 0 ; i < n; ++i){
            scores[3][i] = new Info(i, totalScore[i]);
        }
        
        // 단순 순회
        for (int i = 0; i < 3 + 1; ++i){

            Arrays.sort(scores[i], Collections.reverseOrder());
            
            int rank = 0;
            int preScore = -1;
            int pendingRankCount = 1;
            
            for (int j = 0; j < n; ++j) {
                Info info = scores[i][j];
                int id = info.id;
                int score = info.score;
                
                if (preScore == score){
                    pendingRankCount += 1;
                } else {
                    rank += pendingRankCount;
                    pendingRankCount = 1;
                }
                
                answerArray[i][id] = rank;
                preScore = score;
            }
        }

        printArr(answerArray);
    }

    static void printArr(int[][] answer){

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 3 + 1; ++i){
            for (int j = 0; j < n; ++j) {
                result.append(answer[i][j]).append(" ");
            }
            result.append("\n");
        }

        System.out.println(result);
    }
}
