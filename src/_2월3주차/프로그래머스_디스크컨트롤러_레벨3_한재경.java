import java.util.*;

class Job implements Comparable<Job> { 
    // 전체 작업 큐: 요청시간, 소요시간, 작업번호 순 정렬
    int req;   // 요청시간
    int cost;  // 소요시간
    int num;   // 작업번호

    Job(int cost, int req, int num) {
        this.cost = cost;
        this.req = req;
        this.num = num;
    }
    
    public int compareTo(Job o) {
        if (this.req == o.req) {
            if (this.cost == o.cost) {
                return this.num - o.num;
            }
            return this.cost - o.cost;
        }
        return this.req - o.req;
    }
}

class Njob implements Comparable<Njob> { 
    // 현재까지 가능한 작업 큐: 소요시간, 요청시간, 작업번호 순 정렬
    int req;   // 요청시간
    int cost;  // 소요시간
    int num;   // 작업번호

    Njob(int cost, int req, int num) {
        this.cost = cost;
        this.req = req;
        this.num = num;
    }
    
    public int compareTo(Njob o) {
        if (this.cost == o.cost) {
            if (this.req == o.req) {
                return this.num - o.num;
            }
            return this.req - o.req;
        }
        return this.cost - o.cost;
    }
}

class 프로그래머스_디스크컨트롤러_레벨3_한재경 {
    public int solution(int[][] jobs) { // jobs: {요청시간, 소요시간} 배열
        int sum = 0;
        int time = 0; // 현재시간
        int total = jobs.length;
        
        // 전체 작업 큐: 요청시간 기준 정렬
        PriorityQueue<Job> q = new PriorityQueue<>();
        for (int i = 0; i < total; i++) {
            q.add(new Job(jobs[i][1], jobs[i][0], i));
        }
        
        // 현재까지 가능한 작업 큐: 소요시간 기준 정렬
        PriorityQueue<Njob> nq = new PriorityQueue<>();
        
        // 초기 작업 추가
        Job job = q.poll();
        nq.add(new Njob(job.cost, job.req, job.num));
        
        
        while (!nq.isEmpty()) {
            Njob now = nq.poll(); // 현재 작업 수행
            
            // 만약 현재 시간이 작업 요청시간보다 작다면 시간 점프
            if (time < now.req) {
                time = now.req;
            }
            
            time += now.cost; 
            sum += time - now.req;
            
            // 현재 시간까지 도착한 작업들을 ready 큐에 추가
            while (!q.isEmpty()) {
                Job next = q.peek();
                if (next.req <= time || nq.isEmpty()) {
                    next = q.poll();
                    nq.add(new Njob(next.cost, next.req, next.num));
                } else {
                    break;
                }
            }
        }
        
        return sum / total;
    }
}
