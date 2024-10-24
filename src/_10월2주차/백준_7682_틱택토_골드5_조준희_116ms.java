

import java.awt.datatransfer.Clipboard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 대각선 2: 가능
 * 가로+가로 / 세로+세로 불가
 * X, O 동시 한줄 채우기 불가
 * 
 * x 이김 -> x==o+1
 * o 이김-> x==o
 * 무승부->x=o+1
 */
public class 백준_7682_틱택토_골드5_조준희_116ms {

	static char[] board;
	static int x;
	static int o;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input;
		
		while(!(input = br.readLine()).equals("end")) {
			x = input.length()-input.replaceAll("X", "").length();
			o = input.length()-input.replaceAll("O", "").length();
			
			board = input.toCharArray();
			
			//x가 이김 or 무승부
			if(x==o+1) {
				if(isWon('X') && !isWon('O')
						|| (!isWon('X') && !isWon('O') && x+o==9))
					sb.append("valid\n");
				else
					sb.append("invalid\n");
			}
			//O가 이김
			else if(x==o) {
				if(isWon('O')&& !isWon('X'))
					sb.append("valid\n");
				else
					sb.append("invalid\n");
			}else {
				sb.append("invalid\n");
			}
		}
			
		System.out.println(sb.toString());
			
	}
	//빙고 체크
	private static boolean isWon(char c) {
		
		for(int i = 0; i<9; i++) {
			//가로
			if(i == 0 || i==3 || i==6) {
				if(board[i]==c && board[i]==board[i+1] && board[i+1]==board[i+2]) {
					return true;
				}
			}
			//세로
			if(i==0 || i==1 || i==2) {
				if(board[i]==c && board[i]==board[i+3] && board[i+3]==board[i+6]) {
					return true;
				}
			}
		}
		
		//대각선 위 아래
		if(board[0]==c && board[0]==board[4] && board[4]==board[8]) {
			return true;
		}
		if(board[2]==c && board[2]==board[4] && board[4]==board[6]) {
			return true;
		}
		
		
		return false;
	}
	
}
