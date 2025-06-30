/*
테스트 1 〉	통과 (2.05ms, 82.8MB)
테스트 2 〉	통과 (2.47ms, 78.8MB)
테스트 3 〉	통과 (0.89ms, 77MB)
테스트 4 〉	통과 (0.53ms, 84.2MB)
테스트 5 〉	통과 (1.98ms, 78.4MB)
테스트 6 〉	통과 (2.57ms, 97MB)
테스트 7 〉	통과 (1.81ms, 91.6MB)
테스트 8 〉	통과 (437.11ms, 311MB)
테스트 9 〉	통과 (229.55ms, 190MB)
테스트 10 〉	통과 (614.07ms, 420MB)
테스트 11 〉	통과 (498.44ms, 323MB)
테스트 12 〉	통과 (417.80ms, 308MB)
테스트 13 〉	통과 (348.86ms, 304MB)
테스트 14 〉	통과 (861.20ms, 538MB)
테스트 15 〉	통과 (364.53ms, 319MB)
테스트 16 〉	통과 (359.96ms, 235MB)
테스트 17 〉	통과 (666.26ms, 389MB)
테스트 18 〉	통과 (494.83ms, 328MB)
테스트 19 〉	통과 (335.50ms, 318MB)
테스트 20 〉	통과 (341.67ms, 273MB)
테스트 21 〉	통과 (792.64ms, 522MB)
테스트 22 〉	통과 (604.18ms, 401MB)
테스트 23 〉	통과 (607.75ms, 413MB)
테스트 24 〉	통과 (642.59ms, 411MB)
테스트 25 〉	통과 (586.58ms, 398MB)
테스트 26 〉	통과 (648.61ms, 403MB)
테스트 27 〉	통과 (0.17ms, 90MB)
테스트 28 〉	통과 (0.26ms, 75.3MB)
테스트 29 〉	통과 (0.20ms, 76.3MB)
테스트 30 〉	통과 (0.36ms, 82.6MB)
테스트 31 〉	통과 (0.12ms, 82.7MB)
테스트 32 〉	통과 (0.20ms, 75.9MB)
테스트 33 〉	통과 (0.22ms, 88MB)
테스트 34 〉	통과 (0.17ms, 78.3MB)
테스트 35 〉	통과 (0.18ms, 88.8MB)
*/

// graph
import java.util.*;
class Solution {
    static Set<Integer> fromSet = new HashSet<>();
    static Set<Integer> toSet = new HashSet<>();
    static Map<Integer, Set<Integer> > graph = new HashMap<>();
    static int maxNode;
    static boolean[] visited;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            graph.putIfAbsent(from, new HashSet<Integer>());
            graph.get(from).add(to);
            fromSet.add(from);
            toSet.add(to);
            maxNode = Math.max(maxNode, from);
            maxNode = Math.max(maxNode, to);
        }
        int newNode = 0;
        fromSet.removeAll(toSet);
        for(int val : fromSet){
            if(graph.get(val).size()>=2){
                newNode = val;
                break;
            }
        }
        answer[0] = newNode;
        visited = new boolean[maxNode+1];
        Set<Integer> searchSet = graph.get(newNode);
        for(int first : searchSet){
            while(true){
                visited[first] = true; 
                Set<Integer> nextSet = graph.get(first);
                if(nextSet==null){
                    answer[2]++;
                    break;
                }
                if(nextSet.size()>1){
                    answer[3]++;
                    break;
                }
                first = nextSet.iterator().next();
                if(visited[first]==true){
                    answer[1]++;
                    break;
                }
            }
        }
        return answer;
    }
}
