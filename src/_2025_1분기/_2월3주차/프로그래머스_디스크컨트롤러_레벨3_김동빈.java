import java.util.Comparator;
import java.util.PriorityQueue;

public class 프로그래머스_디스크컨트롤러_레벨3_김동빈 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,3}, {1,9},{3,5}}));
	}
	
	static class Job implements Comparable<Job>{
		
		int duration;
		int requestTime;
		int id;
		public Job(int duration, int requestTime, int id) {
			super();
			this.duration = duration;
			this.requestTime = requestTime;
			this.id = id;
		}
		
		
		@Override
		public int compareTo(Job o) {
			
			int c = Integer.compare(this.duration, o.duration);
			
			if (c == 0) {
				c = Integer.compare(this.requestTime, o.requestTime);
			}
			if (c == 0) {
				c = Integer.compare(this.id, o.id);
			}
			
			return c;
		}
	}
	
	static public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<Job> pendingQ = new PriorityQueue<Job>();
        PriorityQueue<Job> reqpq = new PriorityQueue<>(new Comparator<Job>() {
			@Override
			public int compare(Job o1, Job o2) {
				return Integer.compare(o1.requestTime, o2.requestTime);
			}
        });
        
        for (int i = 0; i < jobs.length; ++i) {
        	Job job = new Job(jobs[i][1], jobs[i][0], i);
        	reqpq.offer(job);
        }
        
        int curTime = 0;
        int totalDuration = 0;
        
        while(!pendingQ.isEmpty() || !reqpq.isEmpty()) {
        	
        	// 다음 작업 후보 넣기
        	if (!reqpq.isEmpty()) {
        		
        		while (!reqpq.isEmpty() && reqpq.peek().requestTime <= curTime) {
        			pendingQ.offer(reqpq.poll());
        		}
        	}
        	
        	if (pendingQ.isEmpty()) {
        		Job job = reqpq.peek();
        		int reqTime = job.requestTime;
        		while (!reqpq.isEmpty() && reqpq.peek().requestTime <= reqTime) {
        			pendingQ.offer(reqpq.poll());
        		}
        	}
        	
        	Job curJob = pendingQ.poll();
        	int duration = curJob.duration;
        	int requestTime = curJob.requestTime;
        	
        	// 작업 하자
        	if (curTime < requestTime) {
        		curTime = requestTime;
        	}
        	curTime += duration;
        	
        	totalDuration += (curTime - requestTime);
        }
        
        answer = totalDuration / jobs.length;
        
        return answer;
    }
	
}
