import java.util.*;
// Map, 경우의 수
class 프로그래머스_의상_레벨2_한재경 {
    public int solution(String[][] clothes) {
        //매일 다른조합, 하나만 입어도 됨
        //서로 다른 옷 조합 수
        Map<String, Integer> codies = new HashMap<>(); //각 옷의 개수
        //카운팅만 해주면 됨
        for (String[] c : clothes) {
            codies.put(c[1], codies.getOrDefault(c[1], 0) + 1); //해당 옷 종류 카운트 ++
        }
        //맵 순회하며 각 value 구하기
        //(1번개수 + 1) * (2번개수 + 1) - 1
        int result = 1;
        for (Map.Entry<String, Integer> entry : codies.entrySet()) {
            result *= (entry.getValue() + 1);
        }
        return result - 1;
    }
}
