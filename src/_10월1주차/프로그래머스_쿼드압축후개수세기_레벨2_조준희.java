
public class 프로그래머스_쿼드압축후개수세기_레벨2_조준희 {
    static int[] answer = new int[2];
    static int[][] numArr;
    public int[] solution(int[][] arr) {
        numArr=arr;
        dfs(0, 0, arr.length);
        return answer;
    }
    
    public void dfs(int r, int c, int size){
        //1칸
        if(size==1){
            answer[numArr[r][c]]++;
            return;
        }
        //전체 확인
        boolean flag = true;
        loop:
        for(int i = r; i<r+size; i++){
            for(int j = c; j<c+size; j++){
                if(numArr[r][c]!=numArr[i][j]){
                    flag=false;
                    break loop;
                }
            }
        }
        //전체 동일
        if(flag){
            answer[numArr[r][c]]++;
            return;
        }
        //4분할 확인
        dfs(r, c, size/2);
        dfs(r+size/2, c, size/2);
        dfs(r, c+size/2, size/2);
        dfs(r+size/2, c+size/2, size/2);
    }
	
}
