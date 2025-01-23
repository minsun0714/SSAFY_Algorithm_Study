package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 구현
 * 119ms
 * 수정 이전: 영양제 좌표(toMove)를 2차원 배열에 넣어서 순회하면서 찾음.
 * 이차원 배열을 남발하지 말자
 */
public class 코드트리_나무타이쿤_골드5_조준희 {
	static int[] dx = {0, 1, 1, 0, -1, -1, -1, 0, 1};
	static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1}; 
	static Queue<Integer> toMove = new LinkedList<>();
	static int N;
	static boolean[][] moved;
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tk.nextToken());
		int T = Integer.parseInt(tk.nextToken());
		
		board = new int[N][N];
		moved = new boolean[N][N];
		
		toMove.add(N-1);
		toMove.add(0);
		toMove.add(N-2);
		toMove.add(0);
		toMove.add(N-1);
		toMove.add(1);
		toMove.add(N-2);
		toMove.add(1);
		
		for(int i = 0; i<N; i++) {
			tk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				board[i][j]=Integer.parseInt(tk.nextToken());
			}
		}
		for(int i = 0; i<T; i++) {
			tk = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(tk.nextToken());
			int n = Integer.parseInt(tk.nextToken());
			
			moveDrug(d, n);
			growTree();
			makeDrug();
		}		
		int sum = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				sum+=board[i][j];
			}
		}
		System.out.print(sum);
	}

	private static void moveDrug(int d, int n) {
		while(!(toMove.isEmpty())){
				int y = toMove.poll();
				int x = toMove.poll();
				int newY = (y+dy[d]*n)%N>=0? (y+dy[d]*n)%N: ((y+dy[d]*n)%N)+N;
				int newX = (x+dx[d]*n)%N>=0? (x+dx[d]*n)%N: ((x+dx[d]*n)%N)+N;
				moved[newY][newX]=true;
				board[newY][newX]++;			
		}		
	}


	private static void growTree() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(moved[i][j]==true) {
					for(int k = 2; k<=8; k+=2) {
						if(i+dy[k]>=0 && i+dy[k]<N && j+dx[k]>=0 && j+dx[k]<N) {
							if(board[i+dy[k]][j+dx[k]]>0) {
								board[i][j]++;
							}
						}
					}
				}
			}
		}
	}
	
	private static void makeDrug() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(moved[i][j]==false&&board[i][j]>=2) {
					board[i][j]-=2;
					toMove.add(i);
					toMove.add(j);
				}
				else if(moved[i][j]==true) {
					moved[i][j]=false;
				}
			}
		}
	}
}
