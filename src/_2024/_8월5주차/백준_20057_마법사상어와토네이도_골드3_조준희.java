import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//528ms 구현
public class 백준_20057_마법사상어와토네이도_골드3_조준희 {
	static int[][] board;
	static int N;
	static int answer;
	static double[][] sandMove = 
		{
				{0, 0, 0.02, 0, 0},
				{0, 0.1, 0.07, 0.01, 0},
				{0.05, -1, 0, 0, 0},
				{0, 0.1, 0.07, 0.01, 0},
				{0, 0, 0.02, 0, 0}
	};
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int x = N/2;
		int y = N/2;
		int d=0;
		int step = 1;
		int cnt = 0;
		
		//배열 순회
		for(int i = 1; i<N*2; i++) {
			cnt++;
			//직진
			for(int j = 0; j<step; j++) {
				x+=dx[d];
				y+=dy[d];
				if(board[x][y]>0) {
					calcSand(x, y);
				}				
			}
			//회전
			d=(d+1)%4;
			//모래 이동 배열 회전
			turn();
			if(cnt==2&&i!=N*2-2) {
				step+=1;
				cnt=0;
			}
		}
		System.out.print(answer);
	}
	
	//모래 이동 배열 반시계 회전
	public static void turn() {
		double[][] newSand = new double[5][5];
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				newSand[5-1-j][i] = sandMove[i][j];
			}
		}
		sandMove = newSand;
	}
	
	//모래 이동
	public static void calcSand(int x, int y) {
		//남은 모래 이동할 좌표
		int remainY=-1;
		int remainX=-1;
		//이동한 모래 합
		int sandSum=0;
		//모래 이동 배열 순회
		for(int i  = -2; i<=2; i++) {
			for(int j = -2; j<=2; j++) {
				//0이면 패스
				if(sandMove[i+2][j+2]==0) {
					continue;
				}
				int newx = x+i;
				int newy = y+j;
				//이동할 모래 양
				int movedSand = (int)Math.floor(board[x][y]*sandMove[i+2][j+2]);
				if(sandMove[i+2][j+2]==-1) {
					movedSand=0;
				}
				sandSum+=movedSand;
				//배열 범위 안
				if(newx>=0 && newy>=0 && newx<N && newy<N) {
					//남은 모래 이동할 좌표 기록
					if(sandMove[i+2][j+2]==-1) {
						remainX=newx;
						remainY=newy;
					}
					board[newx][newy]+=movedSand;
					
				}
				//배열 범위 밖
				else {
					answer+=movedSand;
				}
			}
		}
		//남은 모래 처리
		if(remainY!=-1) {
			board[remainX][remainY]+=board[x][y]-sandSum;
		}
		else {
			answer+=board[x][y]-sandSum;
		}
		//기존 좌표 모래 없애기
		board[x][y]=0;
	}
}
