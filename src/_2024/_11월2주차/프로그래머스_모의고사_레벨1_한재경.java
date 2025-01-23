import java.util.*;
//브루트포스
class 프로그래머스_모의고사_레벨1_한재경 {
    public int[] solution(int[] answers) {
        int[] a = new int[]{1,2,3,4,5};
        int[] b = new int[]{2,1,2,3,2,4,2,5};
        int[] c = new int[]{3,3,1,1,2,2,4,4,5,5};
        int[] scores = new int[3]; //각 넘버의 점수
        int alen = a.length;
        int blen = b.length;
        int clen = c.length;
        
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == a[i % alen]) {
                scores[0]++;
            }
            if (answers[i] == b[i % blen]) {
                scores[1]++;
            }
            if (answers[i] == c[i % clen]) {
                scores[2]++;
            }
        }
        int ms = Math.max(scores[0], Math.max(scores[1], scores[2]));
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (scores[i] == ms) {
                ans.add(i + 1);
            }
        }
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
