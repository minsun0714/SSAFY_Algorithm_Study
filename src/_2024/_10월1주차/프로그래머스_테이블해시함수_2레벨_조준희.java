import java.util.*;

public class 프로그래머스_테이블해시함수_2레벨_조준희 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        int C=data[0].length;
        int R =data.length;
        
     
        Arrays.sort(data, (int[] o1, int[] o2)->{
                if(o1[col-1]-o2[col-1]==0){
                    return o2[0]-o1[0];
                }
                return (o1[col-1]-o2[col-1]);
            }
        );

        int result = 0;
        for(int i = row_begin-1; i<=row_end-1; i++){
            int sum = 0;
            for(int j = 0; j<C; j++){
                sum+=data[i][j]%(i+1);
            }
            if(i>row_begin-1){
                result=result^sum;
            }else{
                result=sum;
            }
        }
        
        return result;
    }
}
