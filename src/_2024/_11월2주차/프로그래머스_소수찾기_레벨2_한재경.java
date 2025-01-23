import java.util.*;
//순열
class 프로그래머스_소수찾기_레벨2_한재경 {
    static Set<Integer> set = new HashSet<>();
    public void permutations(String numbers, int depth, int[] visited, String now){
        if (!now.isEmpty()) {
            int num = Integer.parseInt(now.toString());
            if (isPrime(num)) {
                set.add(num);
            }
        }
        for (int i = 0 ; i < numbers.length(); i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                permutations(numbers, depth + 1, visited, now + numbers.charAt(i));
                visited[i] = 0;
            }
        }
    }
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        else if (number == 2) {
            return true;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    public int solution(String numbers) {
        int answer = 0;
        //자기자신과 1만 약수로 갖는 수
        permutations(numbers, 0, new int[numbers.length()], "");
        
        return set.size();
    }
}
