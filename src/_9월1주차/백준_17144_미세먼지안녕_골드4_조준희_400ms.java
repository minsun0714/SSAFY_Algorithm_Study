import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//400ms 구현
public class 백준_17144_미세먼지안녕_골드4_조준희_400ms {
	static int[][] map;
	static int[][] dust;
	static int R;
	static int C;
	static int[] clnTop;
	static int[] clnBtm;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int i = 0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<C; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) {
					if(map[i-1][j]==-1) {
						clnBtm = new int[] {i, j};
					}
					else {
						clnTop = new int[] {i, j};
					}
				}
			}
		}
		for(int t = 0; t<T; t++) {
			dust = new int[R][C];
			//확산
			for(int i = 0; i<R; i++) {
				for(int j = 0; j<C; j++) {
					if (map[i][j]==-1) {
						continue;
					}
					int toSpread = map[i][j]/5;
					for(int d = 0; d<4; d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						try {
							if (map[nr][nc]==-1) {
								continue;
							}
							dust[nr][nc]+=toSpread;
							map[i][j]-=toSpread;
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
						
					}
			}
			//합치기
			
			for(int i = 0; i<R; i++) {
				for(int j = 0; j<C; j++) {
					map[i][j]+=dust[i][j];
				}
			}
				
			//순환
			int currR = clnTop[0];
			int currC = clnTop[1];
			int d = 0;
			
			int nextR;
			int nextC;
			//위 순환

			while(true) {
				nextR = dr[d]+currR;
				nextC = dc[d]+currC;
				if(nextR>=R || nextR<0 || nextC>=C || nextC<0 || nextR>clnTop[0]) {
					d=(d+1)%4;
					continue;
				}
				if(nextR==clnTop[0] && nextC==clnTop[1]){
					break;
				}
				map[currR][currC] = map[nextR][nextC];
				map[nextR][nextC] = 0;
					
				currR= nextR;
				currC = nextC;
			}
			map[clnTop[0]][clnTop[1]]=-1;
			//아래 순환
			
			currR = clnBtm[0];
			currC = clnBtm[1];
			d = 2;
			while(true) {
				nextR = dr[d]+currR;
				nextC = dc[d]+currC;
				if(nextR>=R || nextR<0 || nextC>=C || nextC<0 || nextR<clnBtm[0]) {
					d=(d-1+4)%4;
					continue;
				}
					if(nextR==clnBtm[0] && nextC==clnBtm[1]){
						break;
					}
					map[currR][currC] = map[nextR][nextC];
					map[nextR][nextC] = 0;
					
					currR= nextR;
					currC = nextC;

			}
			map[clnBtm[0]][clnBtm[1]]=-1;

		}
				
		int sum = 0;
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				sum+=map[i][j];
			}
		}
		System.out.println(sum+2);
		
	}
}
