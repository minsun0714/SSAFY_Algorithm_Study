package etc._4_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 유니온 파인드
public class Main_백준_친구네트워크_골드2_750ms {
	
	static HashMap<String, String> parent;
	static HashMap<String, Integer> count;
	
	public static void main(String[] args) throws Exception {
		
		// 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; ++t) {
			
			int F = Integer.parseInt(br.readLine());
			
			parent = new HashMap<>();
			count = new HashMap<>();
			
			// 친구 관계
			for (int i = 1; i <= F; ++i) {
				st = new StringTokenizer(br.readLine());
				String friend1 = st.nextToken();
				String friend2 = st.nextToken();
				
				count.putIfAbsent(friend1, 1);
				count.putIfAbsent(friend2, 1);
				parent.putIfAbsent(friend1, friend1);
				parent.putIfAbsent(friend2, friend2);
				
				// 유니온 파인드
				int totalFriendCountInNetwork = union(friend1, friend2);
				String friend1Parent = find(friend1); 
				count.put(parent.get(friend1Parent), totalFriendCountInNetwork);
				
				answer.append(totalFriendCountInNetwork).append("\n");
			}
			
		}
		
		System.out.println(answer);
	}

	private static String find(String A) {
		
		String parentA = parent.get(A);
		if (parentA.equals(A)) return parentA;
		
		parent.put(parentA, find(parentA));
		return parent.get(parentA);
	}

	private static int union(String A, String B) {
		
		String parentA = find(A);
		String parentB = find(B);
		
		if (parentA.equals(parentB)) {
			return count.get(parentA);
		}
		
		int totalCount = count.get(parentA) + count.get(parentB);
		parent.put(parentB, find(parentA));
		
		return totalCount;
	}
}
