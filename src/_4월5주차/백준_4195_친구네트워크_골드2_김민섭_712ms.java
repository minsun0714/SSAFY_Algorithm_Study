import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// [클래스] Main
public class 백준_4195_친구네트워크_골드2_김민섭_712ms {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static Map<String, String> group;
	static Map<String, Integer> size;
	
	// [메서드] main
	public static void main(String[] args) throws IOException {
		
		// [T]
		int T = Integer.parseInt(br.readLine());
		
		// (TestCase)
		for (int t = 1; t <= T; t++) {
			
			// [F]
			int F = Integer.parseInt(br.readLine());
			
			// [group]
			group = new HashMap();
			
			// [size]
			size = new HashMap();
			
			for (int f = 0; f < F; f++) {
				st = new StringTokenizer(br.readLine(), " ");
				String a = st.nextToken();
				String b = st.nextToken();
				if (group.get(a) == null) {
					group.put(a, a);
					size.put(a, 1);
				}
				if (group.get(b) == null) {
					group.put(b, b);
					size.put(b, 1);
				}
				union(a, b);
				sb.append(size.get(find(a))).append("\n");
			}
			
		} // (TestCase)
		
		// Print
		System.out.println(sb);
		
	} // [메서드] main
	
	// [메서드] find
	static String find(String A) {
		if (group.get(A).equals(A)) {
			return A;
		}
		group.put(A, find(group.get(A)));
		return group.get(A);
	} // [메서드] find
	
	// [메서드] union
	static void union(String A, String B) {
		if (find(A).equals(find(B))) {
			return;
		}
		size.put(find(A), size.get(find(A)) + size.get(find(B)));
		group.put(find(B), find(A));
	} // [메서드] union
	
} // [클래스] Main
