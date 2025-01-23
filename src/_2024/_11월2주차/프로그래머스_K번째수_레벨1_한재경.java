import java.util.*;
//정렬
class 프로그래머스_K번째수_레벨1_한재경 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int idx = 0;
        //commands: a~b 자르고 정렬했을 때 k번째 숫자
        for (int[] c : commands) {
            int a = c[0] - 1;
            int b = c[1] - 1;
            int k = c[2] - 1;
            List<Integer> l = new ArrayList<>();
            for (int i = a; i <= b; i++) {
                l.add(array[i]);
            }
            Collections.sort(l);
            answer[idx++] = l.get(k);
        }
        
        return answer;
    }
}
