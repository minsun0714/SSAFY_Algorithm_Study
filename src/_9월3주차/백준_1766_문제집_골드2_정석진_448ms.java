import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1766_문제집_골드2_정석진_448ms {
	public static class Node{
		int name;
		int r;
		LinkedHashSet<Node> link;
		Node(){}
		Node(int name){
			this.name = name;
			this.r=0;
			this.link = new LinkedHashSet<>();
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[N+1];
		PriorityQueue<Integer> q = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		for(int i =1;i<=N;i++) {
			nodes[i] = new Node(i);
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int arrive = Integer.parseInt(st.nextToken());
			nodes[start].link.add(nodes[arrive]);
			nodes[arrive].r++;
		}
		
		for(int i =1;i<=N;i++) {
			if(nodes[i].r == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			Node n = nodes[q.poll()];
			sb.append(n.name).append(" ");
			for(Node arv : n.link) {
				arv.r--;
				if(arv.r==0) {
					q.add(arv.name);
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}