import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//45948kb 240ms
//완탐 
public class 백준_15683_감시_골드3_조준희 {
	
	static int N;
	static int M;
	static int[][] map;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited;
	static int[] cctvD;
	static List<CCTV> cctvList = new ArrayList<>();
	static int safe;
	static int answer;
	
	public static void peekDirection(int idx) {
		if(idx>=cctvList.size()) {
			safe=0;
			checkSafe();
			answer=Math.max(safe, answer);
			return;
		}
		CCTV c = cctvList.get(idx);
		for(int i = 0; i<c.dRange; i++) {
			cctvD[idx] = i;
			peekDirection(idx+1);
		}		
	}
	
	public static void checkSafe() {
		int[][] tempMap = new int[N][M];
		for(int i = 0; i<cctvList.size(); i++) {
			CCTV cctv = cctvList.get(i);
			int cd = cctvD[i];
			for(int j = 0; j<4; j++) {
				int x = cctv.x;
				int y = cctv.y;
				for(int n : cctv.d) {
					if((n+cd)%4==j) {
						while(x>=0 && x<N && y>=0 && y<M && map[x][y]!=6) {
							if(tempMap[x][y]!=-1 && map[x][y]==0) {
								safe++;
								tempMap[x][y]=-1;
							}
							x+=dx[j];
							y+=dy[j];
							
						}
					}
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		answer=0;
		
		int blank = 0;
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				switch (map[i][j]) {
					case 1: {
						int[] arr = {0};
						cctvList.add(new CCTV(i, j, arr, 4));
						break;
					}
					case 2: {
						int[] arr = {0, 2};
						cctvList.add(new CCTV(i, j,arr, 2));
						break;
					}
					case 3: {
						int[] arr = {0, 1};
						cctvList.add(new CCTV(i, j, arr, 4));
						break;
					}
					case 4: {
						int[] arr = {0, 1, 2};
						cctvList.add(new CCTV(i, j, arr, 4));
						break;
					}
					case 5: {
						int[] arr = {0, 1, 2, 3};
						cctvList.add(new CCTV(i, j, arr, 1));
						break;
					}
					case 0:{
						blank++;
					}
				}
			}
		}
		visited = new boolean[cctvList.size()][4];
		cctvD = new int[cctvList.size()];
		peekDirection(0);
		System.out.println(blank-answer);
		
	}
	

}
class CCTV{
	int[] d;
	int x;
	int y;
	int dRange;

	public CCTV(int x, int y, int[] d, int dRange) {
		super();
		this.x=x;
		this.y=y;
		this.d = d;
		this.dRange = dRange;
	}
}
