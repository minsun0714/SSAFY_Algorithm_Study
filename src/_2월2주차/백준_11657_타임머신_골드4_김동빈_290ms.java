package etc._2_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 백준_타임머신 {
	
	static class Bus {
		
		int A;
		int B;
		int C;
		public Bus(int a, int b, int c) {
			super();
			A = a;
			B = b;
			C = c;
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 도시의 개수 N과 버스 노선의 개수 M
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        long[] dist = new long[N];
        long INF = Long.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[0] = 0;
        ArrayList<Bus> buses = new ArrayList<Bus>();
        
        // 버스 노선 정보 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());
            
            buses.add(new Bus(A, B, C));
            
        }
        
        // 벨만 포드
        for (int i = 0; i < N - 1; ++i) {
    		// 모든 E에 대하여
    		for (Bus bus : buses) {
    			if (dist[bus.A] == INF) continue;
    			
    			long cur = dist[bus.A] + bus.C;
    			
    			if (cur < dist[bus.B]) {
    				dist[bus.B] = cur;
    			}
    		}
        }
        
        
        // 음의 사이클 판별
        boolean flag = false;
    		
		// 모든 E에 대하여
		for (Bus bus : buses) {
			if (dist[bus.A] == INF) continue;
			long cur = dist[bus.A] + bus.C;
			
			if (cur < dist[bus.B]) {
				flag = true;
				break;
			}
    	}
        
    	// 정답 출력
    	if (flag) {
    		System.out.println(-1);
    	} else {
    		for (int i = 1; i < N; ++i) {
    			System.out.println(dist[i] == INF ? -1 : dist[i]);
    		}
    	}
	}
}
