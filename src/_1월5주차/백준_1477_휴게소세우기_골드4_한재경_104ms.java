import java.io.*;
import java.util.*;

//이분탐색
public class Main {
    static int[] arr;
    static int[] interArr;
    static int n;
    static int m;
    static int L;
    
    // m개를 만족하는 최대구간의 '최소구간' 길이 이분탐색
    public static int binary() {
        // 각 구간마다 해당 간격으로 몇 개 설치 가능한지 sum카운팅
        int l = 1; //최소간격
        int r = L - 1; //최대간격
        int ans = 1;
        while (l <= r) {
            int mid = (l + r) / 2; //설치 간격
            int sum = 0; //설치 개수 카운팅
            
            for (int inter : interArr) { //각 구간 탐색
                if (mid < inter) { //해당 간격보다 설치간격이 좁으면
                    sum += ((inter - 1) / mid); //설치 개수
                }
            }
            if (sum <= m) { //설치 개수 부족하면
                ans = mid; //결과 갱신: 최대간격
                r = mid - 1; //간격 줄이기
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //n 현재 휴게소 개수
        m = Integer.parseInt(st.nextToken()); //m 더지을 개수
        L = Integer.parseInt(st.nextToken()); //L 고속도로 길이
        arr = new int[n + 2]; //휴게소 위치: 고속도로 0과 L지점도 포함
        arr[0] = 0;
        arr[1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i < n + 2; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        interArr = new int[n + 1]; //휴게소 간격
        for (int i = 1; i < n + 2; i++) {
            interArr[i - 1] = arr[i] - arr[i - 1];
        }
        System.out.println(binary());
    }
}
