import java.io.*;
import java.util.*;

//dp
public class 백준_2629_양팔저울_골드3_한재경_112ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] chus = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        //가벼운순, 추 무게 중복 가능
        for (int i = 0; i < n; i++) {
            chus[i] = Integer.parseInt(st.nextToken());
        }
        int s = Arrays.stream(chus).sum(); //추 무게 총합

        // dp[] 두 저울차이 i 가능여부
        boolean[] dp = new boolean[s + 1];
        dp[0] = true;

        for (int chu : chus) {
            // i+chu, i-chu 둘 다 함. 이미 추가한 추 건들지 않도록 리스트에 모으기
            List<Integer> trues = new ArrayList<>(); //true로 바꿀 인덱스모음
            for (int i = 0; i <= s; i++) {
                //무게 i가 true인 경우 추 올리기
                if (dp[i]) {
                    //낮은쪽에 올림: 차 빼기 절댓값
                    trues.add(Math.abs(i - chu));
                    //높은쪽에 올림: 차 더하기
                    trues.add(i + chu);
                }
            }
            for (int idx : trues) {
                if (idx >= 0 && idx <= s) {
                    dp[idx] = true;
                }
            }
        }

        int t = Integer.parseInt(br.readLine()); //확인하려는 구슬 개수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < t; i++) {
            int target = Integer.parseInt(st.nextToken());
            if (target > s) {
                sb.append("N ");
            } else {
                sb.append(dp[target] ? "Y " : "N ");
            }
        }
        System.out.println(sb);
    }
}
