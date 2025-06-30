package etc._1_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// parametric search, 108ms
public class 백준_1477_휴게소세우기_골드4_김동빈_110ms {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		ArrayList<Integer> rests = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			int rest = Integer.parseInt(st.nextToken());
			rests.add(rest);
		}
		rests.add(0); // 첫 점
		rests.add(L); // 끝 점
		
		Collections.sort(rests); // 정렬
		
		ArrayList<Integer> intervals = new ArrayList<>();
		// 간격 넣기, 최대 힙
		for (int i = 0; i <= N; ++i) {
			int interval = rests.get(i + 1) - rests.get(i);
			intervals.add(interval);
		} 
		
		Collections.sort(intervals);
		
		// M 번의 휴게소 짓기
		// l의 길이로 할 수 있을까?
		int answer = L;
		for (int l = 1; l < L; ++l) { // 10^3
			int restCount = 0;
			
			for (int interval : intervals) { // 5 * 10^1
				int rCount = (interval / l);
				if (interval % l == 0 && rCount > 0) {
					rCount -= 1;
				}
				restCount += rCount;
			}
			
			if (restCount <= M) {
				answer = l;
				break;
			}
			
		}
		
 		System.out.println(answer);
	}
}
