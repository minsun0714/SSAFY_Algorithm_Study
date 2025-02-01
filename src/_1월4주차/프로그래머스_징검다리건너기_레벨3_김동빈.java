import java.util.PriorityQueue;

// 구간합, pq
public class 프로그래머스_징검다리건너기_레벨3_김동빈 {
	
	public static class Stone implements Comparable<Stone>{
		int index;
		int count;
		public Stone(int index, int count) {
			this.index = index;
			this.count = count;
		}
		
		@Override
		public int compareTo(Stone o) {
			return -Integer.compare(this.count, o.count);
		}
	}
	
	public int solution(int[] stones, int k) {
        int bridgeLength = stones.length; // 징검 다리 길이
        
        PriorityQueue<Stone> pq = new PriorityQueue<>();
        
        // 최초 k개의 징검다리
        for (int i = 0; i < k; ++i) {
        	pq.offer(new Stone(i, stones[i]));
        }
        
        int minStoneCountKInARow = pq.peek().count;
        
        // 다음 징검다리
        for (int i = k; i < bridgeLength; ++i) {
        	
        	int highestRemovedStoneIndex = i - k;
        	Stone curStone = new Stone(i, stones[i]);
        	pq.offer(curStone);
        	
        	while (pq.peek().index <= highestRemovedStoneIndex) {
        		pq.poll();
			}
        	
        	int maxStoneCount = pq.peek().count;
        	
        	if (maxStoneCount < minStoneCountKInARow) {
        		minStoneCountKInARow = maxStoneCount;
        	}
        }2
        
        int answer = minStoneCountKInARow;
        return answer;
    }
	
}
