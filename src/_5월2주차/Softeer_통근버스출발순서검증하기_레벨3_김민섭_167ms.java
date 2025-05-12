import java.io.*;
import java.util.*;

public class Softeer_통근버스출발순서검증하기_레벨3_김민섭_167ms {

    // [MAIN]
    public static void main(String[] args) throws IOException {
        long answer = 0;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] array = new int[N];

        for (int n = 0; n < N; n++) {
            array[n] = Integer.parseInt(st.nextToken());
        }

        for (int left = 0; left <= N - 3; left++) {
            int counter = 0;
            
            for (int right = N - 1; right >= left + 1; right--) {
                if (array[left] > array[right]) {
                    counter++;
                }
                if (array[left] < array[right]) {
                    answer += counter;
                }
            }
        }

        System.out.println(answer);
    } // [MAIN]
    
}