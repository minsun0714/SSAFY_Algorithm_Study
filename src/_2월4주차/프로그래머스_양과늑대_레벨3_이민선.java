package _2월4주차;

import java.util.*;

// dfs
public class 프로그래머스_양과늑대_레벨3_이민선 {
    static int n;
    static int[] animalInfo;
    static List<List<Integer>> tree;
    static int answer;
    public int solution(int[] info, int[][] edges) {
        n = info.length;
        animalInfo = info;
        tree = new ArrayList<>();
        for (int i=0;i<n;i++){
            tree.add(new ArrayList<>());
        }
        for (int[] edge:edges){
            int p = edge[0], c = edge[1];
            tree.get(p).add(c);
        }
        List<Integer> siblings = new ArrayList<>();
        dfs(0, 0, 0, siblings);

        return answer;
    }

    public static void dfs(int cur, int sheep, int wolf, List<Integer> siblings){
        if (animalInfo[cur] == 0) sheep++;
        else wolf++;

        if (sheep == wolf) return;

        answer = Math.max(answer, sheep);
        List<Integer> nextNodes = new ArrayList<>();
        for (int next:tree.get(cur)){
            nextNodes.add(next);
        }
        for (int s:siblings){
            if (s == cur) continue;
            nextNodes.add(s);
        }

        for (int next:nextNodes){
            dfs(next, sheep, wolf, nextNodes);
        }
    }
}
