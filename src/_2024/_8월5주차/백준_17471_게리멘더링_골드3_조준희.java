import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

//132ms 조합+dfs 탐색 
public class 백준_17471_게리멘더링_골드3_조준희  {
	static int N;
	static Stack<Integer> s = new Stack<>();
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] people;
	static boolean[] visited;
	static boolean[] visited2;
	static int answer=Integer.MAX_VALUE;
	static int all=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph.add(new ArrayList<>());
		people = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			people[i]=Integer.parseInt(st.nextToken());
			all+=people[i];
		}
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int linkN = Integer.parseInt(st.nextToken());
			List<Integer> temp = new ArrayList<>();
			temp.add(i+1);
			for(int j = 0; j<linkN; j++) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			graph.add(temp);
		}
		
		visited = new boolean[N+1];
		for(int i = 1; i<=N/2; i++) {
				makeGroup(i, 1);
		}
		
		if(answer==Integer.MAX_VALUE) {
			System.out.print(-1);
		}
		else {
			System.out.print(answer);
		}
		
		
	}
	
	public static void makeGroup(int n, int start) {
		if(n==0) {
			List<Integer> g1 = new ArrayList<>();
			List<Integer> g2 = new ArrayList<>();
			int g1p = 0;
			for(int i = 1; i<=N; i++) {
				if (visited[i]==true) {
					g1p+=people[i];
					g1.add(i);
				}
				else {
					g2.add(i);
				}
			}
			visited2 = new boolean[N+1];
			checkLink(g1, g1.get(0), visited2);
			boolean isG1Group = isGroup(visited2, g1);
			
			visited2 = new boolean[N+1];
			checkLink(g2, g2.get(0), visited2);
			boolean isG2Group = isGroup(visited2, g2);
			
			if(isG1Group && isG2Group) {
				answer = Math.min(answer, Math.abs(all-g1p-g1p));
			}
			return;
		}
		
		for(int i = start; i<=N; i++) {
			if(!visited[i]) {
				visited[i]=true;
				makeGroup(n-1, i+1);
				visited[i]=false;
			}
		}
	}
	
	public static void checkLink(List<Integer> group, int cur, boolean[] visited) {
		for(int n : graph.get(cur)) {
			if(group.contains(n)&& !visited[n]) {
				visited[n]=true;
				checkLink(group, n, visited);
			}
		}
	}
	
	public static boolean isGroup(boolean[] visited, List<Integer> group) {
		for(int n : group) {
			if(!visited[n]) {
				return false;
			}
		}
		return true;
	}
	
}
