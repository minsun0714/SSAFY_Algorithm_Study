package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//dfs 25312kb 400ms
public class 백준_16234_인구이동_골드4_조준희 {	
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, -1, 0, 1};
	static int[][] map;
	static int[][] unionMap;
	static int N;
	static int L;
	static int R;
	static int cntUnion;
	static int sumUnion;
	public static void dfs(int x, int y, int marker) {
		if(x<0 || x>=N || y<0 || x>=N) {
			return;
		}
		cntUnion++;
		sumUnion+=map[x][y];
			
		for(int i = 0; i<4; i++) {
			int newY = y+dy[i];
			int newX = x+dx[i];
			if(newX>=0 && newX<N && newY>=0 && newY<N&& unionMap[newX][newY]==0) {
				int diff = Math.abs(map[x][y]-map[newX][newY]);
				if(L<=diff && R>=diff) {
					unionMap[x][y]=marker;
					unionMap[newX][newY]=marker;
					dfs(newX, newY, marker);
					
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		unionMap = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		while(true) {
			List<Integer> unionInfo = new ArrayList<>();
			int marker = 1;
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(unionMap[i][j]==0) {
						cntUnion=0;
						sumUnion=0;
						dfs(i, j, marker);
						if(unionMap[i][j]!=0) {
							marker++;
							unionInfo.add(sumUnion/cntUnion);
						}
					}
					
				}
			}
//			System.out.println(Arrays.deepToString(map));
//			System.out.println(Arrays.deepToString(unionMap));
			if(marker==1) {
				break;
			}
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					int unionN = unionMap[i][j];
					if(unionN!=0) {
						map[i][j]=unionInfo.get(unionN-1);
					}
					unionMap[i][j]=0;
					
				}
			}
//			System.out.println();
			cnt++;
		}
		System.out.println(cnt);
	}
}
