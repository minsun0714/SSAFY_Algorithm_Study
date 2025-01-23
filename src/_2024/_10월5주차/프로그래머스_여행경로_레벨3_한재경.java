import java.util.*;
//dfs
class 프로그래머스_여행경로_레벨3_한재경 {
    public static List<String> airports = new ArrayList<>(); //방문 공항들 목록, 띄어쓰기 구분
    public static int[] visited; //i번째 항공권 사용함
    
    public void dfs(String[][] tickets, String until) { //지금까지 공항
        String[] nows = until.split(" ");
        if (nows.length == tickets.length + 1) {
            airports.add(until);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            //방문 전이고 a->b의 a가 현재 공항일 때
            if (visited[i] == 0 && tickets[i][0].equals(nows[nows.length - 1])) {
                visited[i] = 1;
                dfs(tickets, until + " " + tickets[i][1]); //다음공항 넣고 bfs
                visited[i] = 0; //공항 중복 가능하므로 다시 미방문 처리
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        visited = new int[tickets.length];
        dfs(tickets, "ICN");
        
        Collections.sort(airports);
        
        return airports.get(0).split(" ");
    }
}
