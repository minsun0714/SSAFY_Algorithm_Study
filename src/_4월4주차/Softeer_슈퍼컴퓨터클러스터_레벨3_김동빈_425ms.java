package etc._4_4;

import java.io.*;
import java.util.*;

// 이분탐색
public class Softeer_슈퍼컴퓨터클러스터_레벨3_김동빈_425ms {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[] performances = new int[N];
        
        long min = 1;
        long max = Integer.MAX_VALUE;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	int performance = Integer.parseInt(st.nextToken());
            performances[i] = performance;
        }
        
        Arrays.sort(performances);
        long totalCost = bSearch(performances, B, min, max);
        
        System.out.println(totalCost - 1);
	}

	static long bSearch(int[] performances, long B, long min, long max) {
		long l = min;
    	long r = max;
		
		while (l < r) {
			long mid = (r + l) / 2;
			long totalCost = getCost(performances, mid);
			
			if (totalCost <= B) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		
		return l;
	}

	static long getCost(int[] performances, long mid) {
		long totalCost = 0;
		for (int i = 0; i < performances.length; ++i) {
			int performance = performances[i];
			if (performance < mid) {
				totalCost += Math.pow(performance - mid, 2);
			} else {
				break;
			}
		}
		
		return totalCost;
	}
}