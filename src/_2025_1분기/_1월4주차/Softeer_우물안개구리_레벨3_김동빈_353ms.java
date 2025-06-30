package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 353ms, 단순 비교
public class Softeer_우물안개구리_레벨3_김동빈_353ms {
	private static int[] weights;
	private static boolean[] isLoser;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		weights = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++ i) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		// ~ 입력 완료
		
		
		
		// 연관 관계 해석
		isLoser = new boolean[N + 1]; // 초기값 false
		int bestMemberCount = N;
		
		for (int i = 0; i < M; ++i) {
			
			st = new StringTokenizer(br.readLine());
			
			int member1 = Integer.parseInt(st.nextToken());
			int member2 = Integer.parseInt(st.nextToken());
			
			int loserCount = getDefeatMemberCount(member1, member2);
			bestMemberCount -= loserCount;
			
		}
		
		System.out.println(bestMemberCount);
	}
	
	static int isAlreayLoser(int member) {
        if (!isLoser[member]) {
            isLoser[member] = true;
            return 1;
        }
        return 0;
    }
	
	static int getDefeatMemberCount(int member1, int member2) {

	    int member1W = weights[member1];
	    int member2W = weights[member2];
	    
	    int totalLoserCount = 0;

	    if (member1W > member2W) {
	        totalLoserCount += isAlreayLoser(member2);
	    } else if (member1W < member2W) {
	        totalLoserCount += isAlreayLoser(member1);
	    } else { // member1W == member2W
	        totalLoserCount += isAlreayLoser(member1);
	        totalLoserCount += isAlreayLoser(member2);
	    }

	    return totalLoserCount;
	}
	
}