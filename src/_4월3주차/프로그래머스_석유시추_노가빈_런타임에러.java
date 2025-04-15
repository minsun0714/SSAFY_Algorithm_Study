/*
 * 기본적인 정확성 테스트는 전부 통과했지만, 효율성 테스트 5번에서 런타임 에러
정확성  테스트
	테스트 1 〉	통과 (0.06ms, 73MB)
	테스트 2 〉	통과 (0.23ms, 84.8MB)
	테스트 3 〉	통과 (0.06ms, 75.8MB)
	테스트 4 〉	통과 (0.11ms, 86.1MB)
	테스트 5 〉	통과 (0.08ms, 76.4MB)
	테스트 6 〉	통과 (0.57ms, 74.9MB)
	테스트 7 〉	통과 (0.99ms, 74.5MB)
	테스트 8 〉	통과 (0.33ms, 75.7MB)
	테스트 9 〉	통과 (2.15ms, 75.3MB)
효율성  테스트
	테스트 1 〉	통과 (14.91ms, 65MB)
	테스트 2 〉	통과 (62.18ms, 65.5MB)
	테스트 3 〉	통과 (59.94ms, 64.4MB)
	테스트 4 〉	통과 (11.40ms, 81.5MB)
	테스트 5 〉	실패 (런타임 에러)
	테스트 6 〉	통과 (11.62ms, 65.1MB)
 */

class 프로그래머스_석유시추_노가빈_런타임에러 {
    
    static int[] sdr = {-1, 0, 1, 0}; //상 우 하 좌
    static int[] sdc = {0, 1, 0, -1}; //상 우 하 좌
    static boolean[][] visited;
    static boolean[] sum_visited;
    
    static int[][] land;
    static int[] sumlist;
    
    static int srow, scol, cnt;
    
    // DFS
    public static void main(String[] args) {
    	int[][] test1 = new int[][] {
    	    {0, 0, 0, 1, 1, 1, 0, 0},
    	    {0, 0, 0, 0, 1, 1, 0, 0},
    	    {1, 1, 0, 0, 0, 1, 1, 0},
    	    {1, 1, 1, 0, 0, 0, 0, 0},
    	    {1, 1, 1, 0, 0, 0, 1, 1}
    	};

    	int[][] test2 = new int[][] {
    	    {1, 0, 1, 0, 1, 1},
    	    {1, 0, 1, 0, 0, 0},
    	    {1, 0, 1, 0, 0, 1},
    	    {1, 0, 0, 1, 0, 0},
    	    {1, 0, 0, 1, 0, 1},
    	    {1, 0, 0, 0, 0, 0},
    	    {1, 1, 1, 1, 1, 1}
    	};

    	
		System.out.println(solution(test1));
		System.out.println(solution(test2));
    	
	}
    
    
    public static int solution(int[][] inputland) {
        land = inputland;
        
        // static 변수 초기화
        srow = land.length;
        scol = land[0].length;
        
        sumlist = new int[scol];
        
        visited = new boolean[srow][scol]; //처음은 false
        sum_visited = new boolean[scol]; //처음은 false
        
        int max = 0;
        for(int c = 0; c < scol; c++){
            for(int r = 0; r < srow; r++){
                //해당 칸이 1이고, 방문 전인 칸이라면
                if(land[r][c] == 1 && !visited[r][c]){
                    cnt = 0; //숫자세기 시작
                    visit_check(r,c);
                    // System.out.println("cnt : " + cnt);
                    
                    // check_sum_visited();
                
                    //셌던 값을 해당 기억해둔 컬럼들에 추가
                    for(int sc = 0; sc < scol; sc++){
                        if(sum_visited[sc]){ //대상이 된다면
                            sum_visited[sc] = false; //초기화를 한 뒤에
                            sumlist[sc] += cnt; //셌던 값 추가
                            
                            // check_sumlist();
                        }
                    }
                }
            }
            
            if(sumlist[c] > max){
                max = sumlist[c];
            }
        }
        return max;
    }
    
    public static void visit_check(int r, int c){
        
        visited[r][c] = true;
        sum_visited[c] = true;
        cnt++;
        
            
        for(int d = 0; d < sdr.length; d++){
            int dr = r + sdr[d];
            int dc = c + sdc[d];
            
            //상하좌우로 인접한 칸이 1인가? 그리고, 방문하지 않았는가?
            if(n_check(dr,dc) && land[dr][dc] == 1 && !visited[dr][dc]){
                //방문하자
                visit_check(dr, dc); //계속 반복하여 인접한 칸을 보자
            }
        }
    }
    
    public static boolean n_check(int dr, int dc){
        return dr >=0 && dc >= 0 && dr < srow && dc <scol;
    }
    
    public void check_sumlist(){
        for(int c = 0; c < scol; c++){
            System.out.println(sumlist[c]);
        }
        System.out.println("\n");
    }
    
    public void check_sum_visited(){
        for(int c = 0; c < scol; c++){
            System.out.println(sum_visited[c]);
        }
        System.out.println("\n");
    }
    

}




