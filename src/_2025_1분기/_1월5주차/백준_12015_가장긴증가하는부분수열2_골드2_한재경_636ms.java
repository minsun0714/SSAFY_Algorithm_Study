import java.io.*;
import java.util.*;

//가장 긴 증가하는 부분수열, 이분탐색
public class 백준_12015_가장긴증가하는부분수열2_골드2_한재경_636ms {
    public static int binary(int[] arr) {
        List<Integer> lis = new ArrayList<>();
        
        for (int num : arr) {
            int idx = Collections.binarySearch(lis, num);
            
            if (idx < 0) {
                idx = - (idx + 1);
            }
            if (idx == lis.size()) {
                lis.add(num);
            } else {
                lis.set(idx, num);
            }
        }
        return lis.size();
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(binary(arr));
    }
}
