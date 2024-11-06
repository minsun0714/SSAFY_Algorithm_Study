import java.util.*;
//브루트포스
class 프로그래머스_카펫_레벨2_한재경 {
    public boolean makeSquare(int x, int y, int brown, int yellow) {
        int bcnt = 0;
        int ycnt = 0;
        
        for (int i = 0; i < y; i++) { //위아래 두줄 갈색
            bcnt += 2;
        }
        
        for (int i = 0; i < x - 2; i++) {
            bcnt += 2; //양 끝단 갈색
            for (int j = 1; j < y - 1; j++) { //양 끝단 제외 노란색
                ycnt++;                
            }
        }
        if (bcnt == brown && ycnt == yellow) {
            return true;
        }
        return false;
    }
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        //sum = 갈+노
        int sum = brown + yellow;
        //곱해서 sum되는 a,b 조합
        List<String> ab = new ArrayList<>();
        
        for (int i = 1; i <= Math.sqrt(sum); i++) {
            if (sum % i == 0) {
                ab.add("" + sum / i + " " + i);
            }
        }
        //조합 경우 하나씩 브루트포스
        for (String comb : ab) {
            String[] xy = comb.split(" ");
            if (makeSquare(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), brown, yellow)) {
                answer = new int[] {Integer.parseInt(xy[0]), Integer.parseInt(xy[1])};
            }
        }
        
        return answer;
    }
}
