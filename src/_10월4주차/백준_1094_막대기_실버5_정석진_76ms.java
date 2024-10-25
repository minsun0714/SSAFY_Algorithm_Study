import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_1094_막대기_실버5_정석진_76ms {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int result = 0;
        for(int i=0;i<7;i++) {
        	if((len&(1<<i))>0)result++;
        }
        System.out.println(result);

    }
}