package _3월1주차;

import java.io.*;
import java.util.*;

public class Softeer_업무처리_레벨3_이민선_203ms {
    static int h, k, r;
    static List<List<Queue<Integer>>> tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        // 전체 노드의 개수를 구한다.
        int n = 1 << (h + 1);

        tree = new ArrayList<>();
        for (int i=0;i<n;i++){
            tree.add(new ArrayList<>());
            tree.get(i).add(new LinkedList<>());
            tree.get(i).add(new LinkedList<>());
        }

        // 말단 노드 시작 넘버를 구한다.
        int leafStart = 1 << h;
        for (int i=leafStart;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while (st.hasMoreTokens()){
                //홀수번째 날짜에는 왼쪽 부하 직원이 올린 업무를 처리해야 하므로 0번째 인덱스에 넣고
                // 짝수번째 날짜에는 오른쪽 부하 직원이 올린 업무를 처리해야 하므로 1번째 인덱스에 넣는다.
                tree.get(i).get(idx).add(Integer.parseInt(st.nextToken()));
                idx = 1 - idx;
            }
        }
        int answer = 0;
        // r일동안 순회하며
        for (int day=1;day<=r;day++){
            int idx = 1 - day % 2; // 홀수번째 날이면 0, 짝수번째 날이면 1;
            // 루트노드에서 업무 처리
            Queue<Integer> rootQueue = tree.get(1).get(idx);
            if (!rootQueue.isEmpty()) answer += rootQueue.poll();

            // 아래 직원 업무 처리
            for (int i=2;i<n;i++){
                if (tree.get(i).get(idx).isEmpty()) continue;
                int cur = tree.get(i).get(idx).poll();
                // 자기 자신이 왼쪽 부하직원일 경우 (i가 짝수) 0번째에 add, 오른쪽 부하직원일 경우 (i가 홀수) 1번째에 add
                Queue<Integer> parent = tree.get(i / 2).get(i % 2);
                parent.add(cur);
            }
        }
        System.out.println(answer);
    }
}
