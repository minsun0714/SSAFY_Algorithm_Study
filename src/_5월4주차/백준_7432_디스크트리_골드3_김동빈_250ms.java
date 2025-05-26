import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.StringTokenizer;

// 트리
public class 백준_7432_디스크트리_골드3_김동빈_250ms {
	
	static class TrieNode {
		
		String dir;
		TreeMap<String, TrieNode> children;
		
		public TrieNode(String dir) {
			this.dir = dir;
			this.children = new TreeMap<>();
		}
		
		public String print(int depth) {
			StringBuilder base = new StringBuilder();
			base.append(" ".repeat(depth)).append(dir).append("\n");
			
			for (TrieNode node : children.values()) {
				base.append(node.print(depth + 1));
			}
			
			return base.toString();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		TrieNode root = new TrieNode(" "); // root
		
		for (int i = 0; i < n; ++i) {
			
			st = new StringTokenizer(br.readLine(), "\\");
			
			String start = st.nextToken();
			root.children.putIfAbsent(start, new TrieNode(start));
			TrieNode currentNode = root.children.get(start);
			
			while (st.hasMoreTokens()) {
				String dirString = st.nextToken();
				currentNode.children.putIfAbsent(dirString, new TrieNode(dirString));
				TrieNode childNode = currentNode.children.get(dirString);
				
				currentNode = childNode;
			}
		}
		
		for (TrieNode node : root.children.values()) {
			answer.append(node.print(0));
		}
		
		System.out.println(answer);
	}
}