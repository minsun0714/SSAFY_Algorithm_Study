import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//백트래킹, 경우의 수
public class 백준_14888_연산자끼워넣기_실버1_한재경_204ms {
    static int n;
    static int[] nums;
    static int minNum = Integer.MAX_VALUE;
    static int maxNum = Integer.MIN_VALUE;

    static void backTracking(int[] opers, Stack<Integer> operList) {
        boolean isStop = true;
        for (int oper : opers) {
            if (oper != 0) {
                isStop = false;
            }
        }
        if (isStop) {
            makeFunction((Stack<Integer>) operList.clone());
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (opers[i] > 0) { //선택
                opers[i] -= 1;
                operList.push(i); //i번째 연산자 추가
                backTracking(opers, operList); //추가한 경우 all 탐색
                opers[i] += 1; //추가 안한 경우 탐색으로 넘어감
                operList.pop();
            }
        }
    }

    //해당 연산자로 수식 만들기
    static void makeFunction(Stack<Integer> operList) {
        int nowResult = nums[0]; //갱신시킬 계산 결과

        for (int i = 0; i < n - 1; i++) {
            int oper = operList.pop();
            switch (oper) {
                case 0:
                    nowResult += nums[i + 1];
                    break;
                case 1:
                    nowResult -= nums[i + 1];
                    break;
                case 2:
                    nowResult *= nums[i + 1];
                    break;
                case 3:
                    nowResult /= nums[i + 1];
                    break;
            }
        }
        minNum = Math.min(minNum, nowResult);
        maxNum = Math.max(maxNum, nowResult);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n]; //n개 수
        //덧셈0 뺄셈1 곱셈2 나눗셈3
        int[] opers = new int[4]; //연산자 각 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            opers[i] = Integer.parseInt(st.nextToken());
        }

        //계산 앞에서부터
        //식결과 최대, 최솟값
        backTracking(opers, new Stack<>());

        System.out.println(maxNum);
        System.out.println(minNum);
    }
}
