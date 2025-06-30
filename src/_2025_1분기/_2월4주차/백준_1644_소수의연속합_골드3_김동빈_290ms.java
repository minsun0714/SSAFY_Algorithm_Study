package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 290ms, 에라토스테네스의 체, 투 포인터, 누적합
public class 백준_1644_소수의연속합_골드3_김동빈_290ms {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		boolean[] isRemoved = new boolean[N + 1];
		ArrayList<Integer> primes = new ArrayList<>(); // 소수
		ArrayList<Integer> aSum = new ArrayList<>(); // 누적합
		
		primes.add(0);
		aSum.add(0);
		
		for (int i = 2; i <= N; ++i) {
			if (isRemoved[i]) continue;
			
			primes.add(i);
			aSum.add(aSum.get(aSum.size() - 1) + i);
			for (int p = 2; i * p <= N; ++p) {
				isRemoved[i * p] = true;
			}
		}
		
		int l = 0;
		int r = 1;
		int target = N;
		int answer = 0;
		
		int curSum = 2;
		
		while (r < primes.size()) {
			curSum = aSum.get(r) - aSum.get(l);
			
			// 타겟 찾기.
			if (curSum == target) {
				answer++;
			}
			
			// 합 찾기
			if (curSum <= target) {
				r++;
			} else {
				l++;
			}
		}
		
		System.out.println(answer);
	}
}
