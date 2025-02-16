package 이분탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_7453_합이0인네정수_5932ms {
	public static void main(String[] args) throws Exception {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];
		
		int nSquare = n * n;
		int[] preSumArr1 = new int[nSquare];
		int[] preSumArr2 = new int[nSquare];
		
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		int index = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				preSumArr1[index] = (A[i] + B[j]);
				preSumArr2[index] = (C[i] + D[j]);
				
				++index;
			}
		}
		
		// n^2logn^2
		long answer = 0;
		Arrays.sort(preSumArr1);
		Arrays.sort(preSumArr2);
		
		// n^2 logn^2
		for (int i = 0; i < nSquare; ++i) {
			int target = -preSumArr1[i];
			int l = lo(preSumArr2, target);
			int r = hi(preSumArr2, target);
			
			int eqConut = r - l;
			if (l < nSquare && target == preSumArr2[l]) {
				answer += eqConut;
			}
		}
		
		System.out.println(answer);
	}
	
	// lo
	static int lo(int[] arr, int target) {
		
		int l = 0;
		int r = arr.length - 1;
		
		while(l <= r) {
			
			int m = (l + r) / 2;
			if (arr[m] < target) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		
		return l;
	}
	
	// hi
	static int hi(int[] arr, int target) {
		
		int l = 0;
		int r = arr.length - 1;
		
		while(l <= r) {
			
			int m = (l + r) / 2;
			if (arr[m] <= target) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		
		return l;
	}
		
	
	
	
}
