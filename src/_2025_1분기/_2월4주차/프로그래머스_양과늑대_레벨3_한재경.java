import java.util.*;

// dfs, 방문 노드 재탐색 유형: 방문가능 노드 리스트 이용. 매번 new리스트에 복사. 방문하면 현재 노드제거. 방문불가시 제거안하고 리턴 후 추후 방문
class Solution {
    static int[] info;
    static List<Integer>[] link;
    static int ans;
    static void dfs(int now, List<Integer> nexts, int scnt, int wcnt) {
        if (info[now] == 0) scnt++; else wcnt++;
        if (scnt == wcnt) {
            return;
        }
        ans = Math.max(ans, scnt);
        List<Integer> newNexts = new ArrayList<>();
        newNexts.addAll(nexts);
        newNexts.remove(Integer.valueOf(now)); //현재노드 제거
        newNexts.addAll(link[now]); //자식들 추가
        //방문가능노드들 방문
        for (int i : newNexts) {
            dfs(i, newNexts, scnt, wcnt);
        }
    }
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        int n = info.length;
        ans = 0;
        this.info = info;
        link = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            link[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            link[e[0]].add(e[1]);
        }
        dfs(0, new ArrayList<>(Arrays.asList(0)), 0, 0);
        return ans;
    }
}
