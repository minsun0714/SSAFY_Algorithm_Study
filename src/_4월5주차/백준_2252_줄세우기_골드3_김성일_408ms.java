// 위상정렬
// [ 408 ms | 45 mb ]
import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder ouput = new StringBuilder();
	
	static int N;
	static int M;
	static int[] inEdgeCount;
	static ArrayList<Integer>[] nodeList; 
	
	static void sortSort() {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0+1; i < inEdgeCount.length; i++) {
			if(inEdgeCount[i]==0) {
				q.offer(i); 
			}
		}
		while(q.isEmpty()==false) { 
			int nowNode = q.poll(); 
			ouput.append(nowNode).append(" "); 
			for (int i = 0; i < nodeList[nowNode].size(); i++) {
				int nextNode = nodeList[nowNode].get(i);
				inEdgeCount[nextNode]--;	
				if(inEdgeCount[nextNode]==0) {
					q.offer(nextNode);
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		inEdgeCount = new int[N+1];
		nodeList = new ArrayList [N+1];
		for (int i = 0; i < N+1; i++) {
			nodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			nodeList[from].add(to); 
			inEdgeCount[to]++; 
		}
		sortSort();
		System.out.println(ouput);
	}

}
