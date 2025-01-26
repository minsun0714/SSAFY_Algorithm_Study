package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//1131ms, pq
public class Softeer_강의실배정_레벨3_김동빈_1131ms {
	
	static class Lecture implements Comparable<Lecture> {
		int start;
		int end;
		public Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Lecture o) {
			
			return Integer.compare(this.end, o.end);
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Lecture> pq = new PriorityQueue<>(); // 종료 시간 빠른 기준 pq
		
		for (int i = 0; i < N; ++i) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s= Integer.parseInt(st.nextToken());
			int e= Integer.parseInt(st.nextToken());
			
			Lecture lecture = new Lecture(s, e);
			pq.offer(lecture);
		}
		
		int selectedLecturesCount = 0;
		int curTime = 1;
		
		while (!pq.isEmpty()) {
			
			Lecture curLecture = pq.poll();
			int start = curLecture.start;
			int end = curLecture.end;
			
			if (curTime <= start && curTime <= end ) {
				selectedLecturesCount++;
				curTime = end;
			}
			
		}
		
		
		System.out.println(selectedLecturesCount);
	}
}
