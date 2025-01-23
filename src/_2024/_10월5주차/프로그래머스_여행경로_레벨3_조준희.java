import java.util.*;
class 프로그래머스_여행경로_레벨3_조준희 {
    static String[][] ticketArr;
    static boolean[] visited;
    static int ticketnum;
    static String[] answer;
    static Stack<String> route;
    
    public String[] solution(String[][] tickets) {
        
        ticketArr = tickets;
        visited = new boolean[ticketArr.length];
        ticketnum=ticketArr.length;
        route = new Stack<>();
        
        Arrays.sort(ticketArr, (o1, o2)->{
            return o1[1].compareTo(o2[1]);
        });
        
        route.add("ICN");
        btr("ICN", 0);

        return answer;
    }
    public void btr(String cur, int cnt){
        if(answer!=null){
            return;
        }
        if(cnt==ticketnum){
            answer = route.toArray(new String[0]);
            return;
        }
        
        for(int i = 0; i<ticketnum; i++){
            if(!visited[i] && ticketArr[i][0].equals(cur)){
                visited[i]=true;
                route.add(ticketArr[i][1]);
                btr(ticketArr[i][1], cnt+1);
                visited[i]=false;
                route.pop();
            }
        }
    }
}

