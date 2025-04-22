import java.io.*;
import java.util.*;

class 프로그래머스_도넛과막대그래프_레벨2_김민섭 {
    
    // STATIC
    static int topNode;
    static int donut;
    static int stick;
    static int eight;
    
    // [NODE]
    private static class Node {
        int num;
        List<Node> from;
        List<Node> to;
        
        Node (int num) {
            this.num = num;
            this.from = new ArrayList<>();
            this.to = new ArrayList<>();
        }
    } // [NODE]
    
    // [SULUTION]
    public int[] solution(int[][] edges) {
        topNode = 0;
        donut = 0;
        stick = 0;
        eight = 0;
        
        int length = edges.length;
        Map<Integer, Node> nodeMap = new HashMap<>();
        
        int n = 0;
        
        for (int i = 0; i < length; i ++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            if (nodeMap.get(from) == null) {
                nodeMap.put(from, new Node(from));
            }
            if (nodeMap.get(to) == null) {
                nodeMap.put(to, new Node(to));
            }
            
            nodeMap.get(from).to.add(nodeMap.get(to));
            nodeMap.get(to).from.add(nodeMap.get(from));
            
            n = (int) Math.max(Math.max(n, from), to);
        }
        
        // Find Top Node
        for (int key : nodeMap.keySet()) {
            if (nodeMap.get(key).from.size() == 0 && 2 <= nodeMap.get(key).to.size()) {
                topNode = key;
                break;
            }
        }
        
        for (Node node : nodeMap.get(topNode).to) {
            node.from.remove(nodeMap.get(topNode));
            
            Node start = node;
            if (start.to.size() == 0) {
                stick++;
                continue;
            }
            Node next = start.to.get(0);
            
            while (true) {
                if (next.to.size() == 0) {
                    stick++;
                    break;
                }
                
                if (next.to.size() == 2 && next.from.size() == 2) {
                    eight++;
                    break;
                }
                
                if (next == start) {
                    donut++;
                    break;
                }
                next = next.to.get(0);
            }
        }
        
        int[] answer = {topNode, donut, stick, eight};
        return answer;
    } // [SULUTION]
    
}