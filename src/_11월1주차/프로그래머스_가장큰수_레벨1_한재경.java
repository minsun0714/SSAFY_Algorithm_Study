import java.util.*;

//정렬
class 프로그래머스_가장큰수_레벨1_한재경 {
    public String solution(int[] numbers) {
        String answer = "";
        //두 String 합쳤을 때 더 크면 
        String[] ns = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            ns[i] = numbers[i] + "";
        }
        //내림차순: o2를 앞에 붙였을 때 더 크면 o1 뒤로 보내기
        Arrays.sort(ns, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        for (String a : ns) {
            answer += a;
        }
        if (ns[0].equals("0")) { //모든 수가 0인 경우
           return "0";
        }
        return answer;
    }
}
