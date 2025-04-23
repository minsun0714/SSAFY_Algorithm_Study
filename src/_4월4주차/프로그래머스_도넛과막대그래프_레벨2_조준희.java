import java.util.*;
class 프로그래머스_도넛과막대그래프_레벨2_조준희 {
    static int[] in_degree;
    static int[] out_degree;
    static Map<Integer, List<Integer>> graph;
    
    public int[] solution(int[][] edges) {
        
        final int maxN = 1000000;
        in_degree = new int[maxN+1]; 
        out_degree = new int[maxN+1]; 
        graph = new HashMap<>();
        
        for(int[] edge : edges){
            in_degree[edge[1]]++;
            out_degree[edge[0]]++;
            graph.putIfAbsent(edge[0], new ArrayList<Integer>());
            graph.get(edge[0]).add(edge[1]);
        }
        
        int center = 0;
        //생성 접점 찾기
        for(int i = 1; i<=maxN; i++){
            if(out_degree[i]>=2&&in_degree[i]==0){
                center = i;
                break;
            }
        }
        
        int[] answer = new int[4];
        answer[0]=center;
        
        //그래프 모양 찾기
        for(Integer start : graph.get(center)){
            answer[check(start, start, true)]++;
        }
        
        return answer;
    }
    //그래프 모양 찾기
    public static int check(int curr, int start, boolean is_first){
        List<Integer> next_list = graph.get(curr);
        //도넛
        if(!is_first){
            if(curr==start){
                return 1;
            }
        }        
        //막대
        if(next_list==null || next_list.size()==0){            
            return 2;
        }
        //8자
        if(out_degree[curr]==2){
            if(in_degree[curr]==2 || in_degree[curr]==3)
            return 3;
        }
        
        return check(next_list.get(0), start, false);
    }
}
