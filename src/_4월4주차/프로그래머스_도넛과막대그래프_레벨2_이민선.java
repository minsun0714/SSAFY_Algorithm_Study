// 구현
class 프로그래머스_도넛과막대그래프_레벨2_이민선 {
    public int[] solution(int[][] edges) {
        int n = 0;

        for (int[] edge:edges){
            int a = edge[0];
            int b = edge[1];
            n = Math.max(Math.max(a,b), n);
        }

        boolean[] isNode = new boolean[n];

        int[] indegree = new int[n];
        int[] outdegree = new int[n];
        for (int[] edge:edges){
            int a = edge[0];
            int b = edge[1];
            indegree[b - 1]++;
            outdegree[a - 1]++;

            isNode[a - 1] = true;
            isNode[b - 1] = true;
        }
        int[] answer = new int[4];
        for (int i=0;i<n;i++){
            // 생성한 정점의 번호
            if (outdegree[i] >= 2 && indegree[i] == 0) {
                answer[0] = i + 1;
            }
        }

        for (int i=0;i<n;i++){
            int out = outdegree[i];
            int in = indegree[i];

            if (isNode[i] && out == 0) answer[2]++;
            else if (out == 2 && in >= 2) answer[3]++;
        }
        answer[1] = outdegree[answer[0] - 1] - answer[2] - answer[3];
        return answer;
    }
}