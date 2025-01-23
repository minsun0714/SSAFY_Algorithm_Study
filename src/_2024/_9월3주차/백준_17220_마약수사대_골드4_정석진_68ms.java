import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_17220_마약수사대_골드4_정석진_68ms {
	public static class Node{
		int r;
		int name;
		HashSet<Node> link;
		Node(){}
		Node(int i){
			this.name = i;
			this.r= 0;
			this.link = new HashSet<Node>();
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[N];
		Queue<Node> q = new LinkedList<>();
		HashSet<Integer> catched = new HashSet<>();
		int result=0;
		for(int i =0;i<N;i++) {
			nodes[i] = new Node(i);
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = st.nextToken().charAt(0)-'A';
			int end = st.nextToken().charAt(0)-'A';
			//System.out.println(start+" "+end);
			nodes[start].link.add(nodes[end]);
			nodes[end].r++;
		}
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		for(int i =0;i<K;i++) {
			catched.add(st.nextToken().charAt(0)-'A');
		}
		
		int producer = 0;
		for(int i=0;i<N;i++) {
			if(nodes[i].r==0 && !nodes[i].link.isEmpty() && !catched.contains(i)) {
				q.add(nodes[i]);
				producer++;
			}
		}
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			result++;
			for(Node l : n.link) {
				if(!catched.contains(l.name)) {
					q.add(l);
				}
			}
		}
		result-=producer;
		System.out.println(result);
	}
}
