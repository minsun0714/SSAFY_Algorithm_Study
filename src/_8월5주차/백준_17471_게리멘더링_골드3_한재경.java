import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//144ms
public class 백준_17471_게리멘더링_골드3_한재경 {
    //메인 정보는 정적할당
    static int N;
    static int[] population;
    static List<List<Integer>> adjList;
    static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        adjList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int adjCount = Integer.parseInt(st.nextToken());
            for (int j = 0; j < adjCount; j++) {
                int neighbor = Integer.parseInt(st.nextToken());
                adjList.get(i).add(neighbor);
            }
        }

        // 재귀를 사용해 구역을 두 그룹으로 나누기
        divideDistricts(new Stack<>(), new Stack<>(), 1);

        System.out.println(minDifference == Integer.MAX_VALUE ? -1 : minDifference);
    }

    //1번부터 N번 구역까지 A/B구역에 각각 넣는 경우 Stack
    static void divideDistricts(Stack<Integer> groupA, Stack<Integer> groupB, int index) {
        if (index > N) { //꽉 차면
            if (groupA.size() > 0 && groupB.size() > 0) { //한구역에 몰빵 아니면
                if (isConnected(groupA) && isConnected(groupB)) { //두 구역 모든 연결 확인하고
                    int populationA = 0, populationB = 0; //각 인구 계산
                    for (int a : groupA) populationA += population[a];
                    for (int b : groupB) populationB += population[b];
                    minDifference = Math.min(minDifference, Math.abs(populationA - populationB)); //최소 인구차 계산
                }
            }
            return;
        }

        // 현재 구역을 groupA에 넣는 경우
        groupA.add(index);
        divideDistricts(groupA, groupB, index + 1);
        groupA.pop();

        // 현재 구역을 groupB에 넣는 경우
        groupB.add(index);
        divideDistricts(groupA, groupB, index + 1);
        groupB.pop();
    }

    //특정 구역 개체 모두 연결되어있는지 여부 - bfs
    static boolean isConnected(List<Integer> group) {
        if (group.size() == 0) return false;

        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(group.get(0));
        visited[group.get(0)] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adjList.get(node)) {
                if (group.contains(neighbor) && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }

        return count == group.size();
    }
}
