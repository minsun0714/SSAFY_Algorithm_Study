import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//108 ms 시뮬레이션
public class SWEA_4013_특이한자석_조준희 {
    static boolean[][] magnet = new boolean[4][8];
    static int[] top;
    final static int right = 2;
    final static int left = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TEST = Integer.parseInt(br.readLine());
        for(int T = 1; T<=TEST; T++){
            int N = Integer.parseInt(br.readLine());
            for(int i = 0; i<4; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j<8; j++){
                    //1: true: S
                    magnet[i][j]= st.nextToken().equals("1");
                }
            }
            top = new int[4];
            for(int i = 0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int mNum = Integer.parseInt(st.nextToken())-1;
                int d = -Integer.parseInt(st.nextToken());

                int[] newTop = Arrays.copyOf(top, 4);
                newTop[mNum]=getIndex(newTop[mNum],d);
                int newD = d;
                for(int j = mNum+1; j<4; j++){
                    newD = -newD;
                    int lm = j-1;
                    int rm = j;
                    if(magnet[lm][getIndex(top[lm], right)]
                    !=magnet[rm][getIndex(top[rm], left)]){
                        newTop[rm]=getIndex(newTop[rm], newD);
                    }
                    else{
                        break;
                    }
                }
                newD = d;
                for(int j = mNum-1; j>=0; j--){
                    newD = -newD;
                    int lm = j;
                    int rm = j+1;
                    if(magnet[lm][getIndex(top[lm], right)]
                            !=magnet[rm][getIndex(top[rm], left)]){
                        newTop[lm]=getIndex(newTop[lm], newD);
                    }
                    else{
                        break;
                    }
                }
                top = Arrays.copyOf(newTop, 4);

            }
            int answer = 0;
            for(int i = 0; i<4; i++){
                if(magnet[i][top[i]]){

                    answer += (int) Math.pow(2, i);
                }
            }
            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
    public static int getIndex(int start, int move){
        return (start+8+move)%8;
    }
}
