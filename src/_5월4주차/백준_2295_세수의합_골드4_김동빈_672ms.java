import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 백준_2295_세수의합_골드4_김동빈_672ms {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] numbers = new int[n]; 
		
		for (int i = 0; i < n; ++i) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		ArrayList<Integer> sumNumbers = new ArrayList<>();
		
		for (int i = 0; i < n; ++i) {
			for (int j = i; j < n; ++j) {
				sumNumbers.add(numbers[i] + numbers[j]);
			}
		}
		
		Arrays.sort(numbers);
		Collections.sort(sumNumbers);
		int answer = -1;
		
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				
				int target = numbers[j] - numbers[i];
				
				int index = Collections.binarySearch(sumNumbers, target);
				if (index >= 0) { // 있음
					int a = sumNumbers.get(index) + numbers[i];
					if (answer < a) {
						answer = a;
					}
				}
			}
		}
			
		System.out.println(answer);
	}
}