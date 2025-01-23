import java.util.*;
class 프로그래머스_H-Index_레벨2_한재경 {
    public int solution(int[] citations) { //i번째 논문 인용 횟수
        //논문 n편중 h번 이상 인용된 논문 h편 이상 -> 각 h중 최댓값
        //내림차순해서 앞에서부터 cnt >= citations[i] 가능
        Arrays.sort(citations);
        int cnt = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (cnt < citations[i]) {
                cnt++;
            }
            else {
                break;
            }
        }
        return cnt;
    }
}
