package _3월3주차;

import java.util.*;

public class 프로그래머스_디스크컨트롤러_레벨3_이민선 {
  static class Node implements Comparable<Node> {
    int idx;
    int reqTime;
    int duration;

    Node (int idx, int reqTime, int duration) {
      this.idx = idx;
      this.reqTime = reqTime;
      this.duration = duration;
    }

    // waitQ로부터 노드가 나가는 순서 (문제에서 제시해준 것과 동일)
    @Override
    public int compareTo(Node anotherNode){
      if (this.duration == anotherNode.duration){
        if (this.reqTime == anotherNode.reqTime) return Integer.compare(this.idx, anotherNode.idx);
        return Integer.compare(this.reqTime, anotherNode.reqTime);
      }
      return Integer.compare(this.duration, anotherNode.duration);
    }

    @Override
    public String toString(){
      return "(" + idx + "," + reqTime + "," + duration + ")";
    }
  }
  static Node[] nodeList;
  static PriorityQueue<Node> waitQ;
  public int solution(int[][] jobs) {
    nodeList = new Node[jobs.length];
    int idx = 0;
    for (int[] job:jobs){
      Node node = new Node(idx, job[0], job[1]);
      nodeList[idx++] = node;
    }

    // waitQ에 들어오는 순서는 reqTime의 순서와 동일 (compareTo 메서드의 용도와 구분지어 생각할 것)
    Arrays.sort(nodeList, (a, b) -> Integer.compare(a.reqTime, b.reqTime));

    waitQ = new PriorityQueue<Node>();

    int currentTime = 0;
    int turnaroundTime = 0;

    idx = 0;
    while (idx < jobs.length || !waitQ.isEmpty()){
      // 직전 노드 작업을 하던 중 들어온 노드를 waitQ에 모두 넣음
      while (idx < jobs.length && nodeList[idx].reqTime <= currentTime){
        waitQ.offer(nodeList[idx]);
        idx++;
      }
      // 직전 노드 작업을 하던 중 들어온 노드가 없어서 waitQ에 아무것도 안들어감. 근데 currentTime이 아직 다음 노드의 요청 시간이 안된 경우
      if (waitQ.size() == 0 && currentTime < nodeList[idx].reqTime)
        // 현재 시각을 다음 노드의 요청 시간으로 강제로 늦춤.
        currentTime = nodeList[idx].reqTime;

      // 이부분 while문으로 했다가 고친 이유:
      // while문을 돌리다가 waitQ에 우선순위가 높은 노드가 중간에 들어올 수도 있기 때문에, 노드 하나가 poll될 때마다 주기적으로 waitQ를 확인해야 함.
      if (!waitQ.isEmpty()) {
        Node cur = waitQ.poll();
        currentTime += cur.duration;
        turnaroundTime += (currentTime - cur.reqTime);
      }
    }

    return turnaroundTime / jobs.length;
  }

}
