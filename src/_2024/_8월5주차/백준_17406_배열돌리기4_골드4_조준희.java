import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//508ms 구현
public class 백준_17406_배열돌리기4_골드4_조준희 {
    static Stack<Integer> cmdOrder = new Stack<>();
    static int K;
    static int N;
    static int M;
    static int[][] board;
    static int[][] cmd;
    static int answer=Integer.MAX_VALUE;

    public static void makeOrder(){
        if(cmdOrder.size()==K){
            int[][] tempBoard = new int[N][M];
            for(int i = 0; i<N; i++){
                System.arraycopy(board[i], 0, tempBoard[i],0,  M);
            }
            for(Integer n : cmdOrder){
                turn(cmd[n][0], cmd[n][1], cmd[n][2], tempBoard);
            }
            int minR = Integer.MAX_VALUE;
            for(int i = 0; i<N; i++){
                int sumR = 0;
                for(int j = 0; j<M; j++){
                    sumR+=tempBoard[i][j];
                }
                minR = Math.min(minR, sumR);
            }
            answer = Math.min(answer, minR);
        }
        for(int i = 0; i<K; i++){
            if(!cmdOrder.contains(i)){
                cmdOrder.add(i);
                makeOrder();
                cmdOrder.pop();
            }
        }
    }

    public static void turn(int r, int c, int s, int[][]tempBoard){
        r--;
        c--;
        int[][] copy = new int[s*2+1][s*2+1];
        for(int i = -s; i<=s; i++){
            System.arraycopy(tempBoard[r+i],c-s, copy[i+s], 0, s*2+1);
        }

        for(int i = -s; i<=s; i++){
            for(int j = -s; j<=s; j++){
                if(i>j && j<0 && i<=-j){
                    tempBoard[r+i-1][c+j]=copy[i+s][j+s];
                }
                else if(i<j && j>0 && i>=-j){
                    tempBoard[r+i+1][c+j]=copy[i+s][j+s];
                }
                else if(i<0){
                    tempBoard[r+i][c+j+1]=copy[i+s][j+s];
                }
                else if(i>0){
                    tempBoard[r+i][c+j-1]=copy[i+s][j+s];
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        cmd = new int[K][3];


        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
                cmd[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        makeOrder();
        System.out.print(answer);
    }
}
