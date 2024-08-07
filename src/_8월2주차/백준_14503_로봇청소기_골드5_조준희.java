package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//132ms 구현

/**
 * 초기 맵: 1, 0
 * 시작
 * 1. 현재 칸 청소(-1)
 * 2-1 90도씩 4번 확인- 청소 안한 칸이면 전진
 * 2-2 없으면 후진
 */
public class 백준_14503_로봇청소기_골드5_조준희 {
	static int[][] floor;
	static int[] dy = {-1, 0, 1, 0}; 
	static int[] dx = {0, 1, 0, -1};
	static int N;
	static int M;
	static int x;
	static int y;
	static int d;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		floor = new int [N][M];
		
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				floor[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			if(floor[y][x]==0) {
				cnt++;
				floor[y][x]--;
			}
			if(!checkDirt()){
				int newD = (d+2)%4;
				int newY = y+dy[newD];
				int newX = x+dx[newD];
				if(newY>=0 && newY<N && newX>=0 && newX<M && floor[newY][newX]!=1) {
					y = newY;
					x = newX;
				}
				else if((newY>=0 && newY<N && newX>=0 && newX<M && floor[newY][newX]==1)) {
					break;
				}
			}
		}
		System.out.print(cnt);
	}
	private static boolean checkDirt() {
		for(int i = 3; i>=0; i--) {
			int newD = (d+i)%4;
			int newY = y+dy[newD];
			int newX = x+dx[newD];
			if(newY>=0 &&newY<N && newX>=0 && newX<M && floor[newY][newX]==0) {
				d = newD;
				x = newX;
				y = newY;
				return true;
			}
		}
		return false;
		
	}
}
