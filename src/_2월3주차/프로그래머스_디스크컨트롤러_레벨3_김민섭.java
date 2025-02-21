import java.io.*;
import java.util.*;

class 프로그래머스_디스크컨트롤러_레벨3_김민섭 {
    // [Solution]
    public int solution(int[][] jobs){
        // Count
        int count = jobs.length;
        
        // Tasks
        Task[] tasks = new Task[count];
        for (int i = 0; i < count; i++) {
            int index = i;
            int request = jobs[i][0];
            int amount = jobs[i][1];
            tasks[i] = new Task(index, request, amount);
        }
        Arrays.sort(tasks, (a, b) -> {
            return a.request - b.request;
        });
        
        // Task Queue
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        // Time
        int time = 0;
        // Queued
        int queued = 0;
        // Completed
        int completed = 0;
        // Answer
        int answer = 0;
        
        // CalCulate
        while (completed < count) {
            if (!taskQueue.isEmpty()) {
                Task currentTask = taskQueue.poll();
                time += currentTask.amount;
                answer += time - currentTask.request;
                completed++;
            }
            while (queued < count && tasks[queued].request <= time) {
                taskQueue.offer(tasks[queued++]);
            }
            if (taskQueue.isEmpty() && completed < count) {
                time = tasks[queued].request;
            }
        }
        
        // Return Answer
        answer /= count;
        return answer;
    } // [/Solution]
    
    // [Task]
    private static class Task implements Comparable<Task>{
        int index;
        int request;
        int amount;
        
        public Task() {
        }
        
        public Task(int index, int request, int amount) {
            this.index = index;
            this.request = request;
            this.amount = amount;
        }
        
		@Override
		public int compareTo(Task o) {
			if (this.amount == o.amount) {
				if (this.request == o.amount) {
					return this.index - o.index;
				}
				return this.request - o.amount;
			}
			return this.amount - o.amount;
		}
    } // [/Task]
}
