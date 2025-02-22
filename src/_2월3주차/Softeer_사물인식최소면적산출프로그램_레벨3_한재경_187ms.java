import java.util.*;
import jaca.io.*;

//조합
public class Softeer_사물인식최소면적산출프로그램_레벨3_한재경_187ms {
    static int k;
    static int ans;
    static List<List<int[]>> c;
    //k개 중에 1개씩 고르기
    public static void combinations(int depth, int u, int d, int l, int r) {
        if ((u-d)*(r-l) >= ans) {return;}
        if (depth == k) { //다고름
            ans = Math.min(ans, (u-d)*(r-l));
           // System.out.println(ans);
            return;
        }
        
        //해당색깔의 끝까지 선택
        for (int i = 0; i < c.get(depth).size(); i++) {
            //depth컬러 i번째 점
            int[] xy = c.get(depth).get(i);
            combinations(depth+1, Math.max(u, xy[1]), Math.min(d, xy[1]),
                         Math.min(l, xy[0]), Math.max(r, xy[0])); //다음색으로
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //n개 점
        k = Integer.parseInt(st.nextToken()); //k개 색깔
        c = new ArrayList<>(); //색깔:위치
        ans = Integer.MAX_VALUE;
        
        for (int i = 0; i < k; i++) {
            c.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + 1000; //좌표값 자연수로 이동
            int y = Integer.parseInt(st.nextToken()) + 1000;
            int color = Integer.parseInt(st.nextToken()) - 1;
            c.get(color).add(new int[]{x, y});
        }
        combinations(0, 0, 2000, 2000, 0);
        //선분도 직사각형 (면적 0) - 일직선상이면 최소
        //모든 색 포함하는 최소 직사각형 면적
        
        System.out.println(ans);
        
    }
}
