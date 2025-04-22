import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//재귀
public class 백준_1030_프렉탈평면_골드3_조준희_112ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s, N, K, R1, R2, C1, C2;

        s = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());

        int size = N;
        if(s>1) size = (int) Math.pow(N, s);

        for(int r = R1; r<=R2; r++){
            for(int c = C1; c<=C2; c++){
                System.out.print(check(N, K, r, c, size));
            }
            System.out.println();
        }
    }


    public static int check(int n, int k, int r, int c, int size){
        if(size==1){
            return 0;
        }
        int unit_size = size/n;
        int black_start = (n-k)/2;
        int center_st = black_start*unit_size;
        int center_end = center_st+unit_size*k;
        if(r>=center_st && r<center_end && c>=center_st && c<center_end){
            return 1;
        }
        return check(n, k, r%unit_size, c%unit_size, unit_size);
    }
}
