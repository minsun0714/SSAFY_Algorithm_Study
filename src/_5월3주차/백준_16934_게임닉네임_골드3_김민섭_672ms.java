import java.io.*;
import java.util.*;

public class 백준_16934_게임닉네임_골드3_김민섭_672ms {
	
	// [NODE]
	private static class Node {
		Map<Character, Node> child;
		int checker;
		int end;
		
		Node() {
			this.child = new HashMap<>();
			this.checker = 0;
			this.end = 0;
		}
	} // [NODE]
	
	// [TRIE]
	private static class Trie {
		Node root;
		
		Trie() {
			this.root = new Node();
		}
		
		String insert(String word) {
			String str = "";
			boolean flag = false;
			
			Node node = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				
				if (node.child.get(c) == null) {
					node.child.put(c, new Node());
				}
				
				node = node.child.get(c);
				
				node.checker++;
				
				if (i == word.length() - 1) {
					node.end++;
				}
				
				if (!flag) {
					str += c + "";
					if (i == word.length() - 1 && node.end != 1) {
						str += node.end + "";
					}
				}
				
				if (node.checker == 1) {
					flag = true;
				}
				
			}
			
			return str;
		}
	} // [TRIE]
	
	// [MAIN]
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Trie trie = new Trie();
		
		for (int n = 0; n < N; n++) {
			String nickname = br.readLine();
			sb.append(trie.insert(nickname)).append("\n");
		}
		
		System.out.println(sb);
	} // [MAIN]
	
}
