import java.io.*;
import java.util.*;

public class 백준_7432_디스크트리_골드3_김민섭_276ms {
	
	// STATIC
	static StringBuilder sb;
	
	// [DIRECTORY]
	private static class Directory {
		int level;
		Map<String, Directory> child;
		
		Directory(int level) {
			this.child = new TreeMap<>();
			this.level = level;
		}
		
		void query() {
			for (String key : this.child.keySet()) {
				for (int i = 0; i < this.level; i++) {
					sb.append(" ");
				}
				sb.append(key).append("\n");
				this.child.get(key).query();
			}
		}
	} // [DIRECTORY]
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		sb = new StringBuilder();
		
		Directory root = new Directory(0);
		
		int N = Integer.parseInt(br.readLine());
		
		for (int n = 0; n < N; n++) {
			
			Directory curr = root;
			
			st = new StringTokenizer(br.readLine(), "\\");
			
			int sc = st.countTokens();
			for (int s = 0; s < sc; s++) {
				String str = st.nextToken();
				
				if (curr.child.get(str) == null) {
					curr.child.put(str, new Directory(curr.level + 1));
				}
				
				curr = curr.child.get(str);
//				System.out.println(s + " | " + str + " | " + curr.level);
			}
		}
		
		root.query();
		
		System.out.println(sb);
	} // [MAIN]
	
}