import java.util.LinkedList;
import java.util.Queue;

class 프로그래머스_타겟넘버_레벨2_정석진 {
	public static class Element {
		int depth;
		int num;
		Element(int num, int depth) {
			this.depth = depth;
			this.num = num;
		}
	}

	public static int solution(int[] numbers, int target) {
		int answer = 0;
		int size = numbers.length;
		System.out.println(size);
		Queue<Element> q = new LinkedList<>();
		q.add(new Element(0, 0));
		while (!q.isEmpty()) {
			Element e = q.poll();
			//System.out.println(e.num+" "+e.depth);
			if (e.depth == size) {
				if (e.num == target) {
					answer++;
				}
				continue;
			}

			q.add(new Element(e.num + numbers[e.depth], e.depth + 1));
			q.add(new Element(e.num - numbers[e.depth], e.depth + 1));
		}
		return answer;
	}
}