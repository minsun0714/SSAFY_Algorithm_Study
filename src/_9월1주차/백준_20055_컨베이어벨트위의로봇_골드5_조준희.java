import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class 백준_20055_컨베이어벨트위의로봇_골드5_조준희 {
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int start = 0;
		int end = N-1;
		int[] belt = new int[2*N];
		Deque<Integer> robot = new ArrayDeque<>();
		int broke = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<2*N; i++) {
			belt[i]=Integer.parseInt(st.nextToken());
		}
		
		int cnt=0;
		//반복
		while(broke<K) {
			//횟수 +1
			cnt++;
			
			//컨베이어 돌리기
			start = (belt.length*2+start-1)%belt.length;
			end = (belt.length*2+end-1)%belt.length;
			
			//로봇 이동
			int robotN = robot.size();
			for(int i = 0; i<robotN; i++) {
				//이동할 로봇
				int r = robot.poll();
				
				//로봇위치가 end 아니면
				if(r!=end) {
					//1칸 앞 내구도 확인
					if(belt[(r+1)%belt.length]>0) {
						//1칸 앞 로봇 유무 확인
						if(robot.size()==0||(robot.size()>0 && robot.peekLast()!=(r+1)%belt.length)) {
							//1칸 앞 내구도 깎고 0 체크
							belt[(r+1)%belt.length]--;
							if(belt[(r+1)%belt.length]==0) {
								broke++;
							}
							//1칸 앞 end 아니면 위치 업뎃
							if((r+1)%belt.length!=end) {
								robot.add((r+1)%belt.length);
							}
						}
						//1칸 앞 로봇 있으면 이동X
						else {
							robot.add(r);
						}
					}
					//1칸 앞 내구도 0이면 이동X
					else {
					robot.add(r);
					}
				}
			}
			//start에 로봇 올리기
			if(belt[start]>0) {
				robot.add(start);
				belt[start]--;
				if(belt[start]==0) {
					broke++;
				}
				
			}
			System.out.println(Arrays.toString(belt ));
		}
	
		System.out.println(cnt);
	}
}
