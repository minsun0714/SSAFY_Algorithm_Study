package _2월3주차;

import java.io.*;
import java.util.*;

public class Softeer_사물인식최소면적산출프로그램_레벨3_이민선_194ms {
  static int N, K;
  static List<List<int[]>> list;
  static int minWidth = 2000 * 2000 + 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    list = new ArrayList<>();
    for (int i=0;i<=K;i++){
      list.add(new ArrayList<>());
    }
    for (int i=0;i<N;i++){
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());

      list.get(k).add(new int[]{x, y});
    }

    backtracking(1, 1000, 1000, -1000, -1000);
    System.out.println(minWidth);
  }

  public static void backtracking(int depth, int minX, int minY, int maxX, int maxY){
    if ((maxX - minX) * (maxY - minY) >= minWidth) return;

    if (depth == K + 1){
      minWidth = Math.min(minWidth, (maxX - minX) * (maxY - minY));
      return;
    }

    for (int i=0;i<list.get(depth).size();i++){
      int[] cur = list.get(depth).get(i);
      backtracking(depth + 1, Math.min(minX, cur[0]), Math.min(minY, cur[1]), Math.max(maxX, cur[0]), Math.max(maxY, cur[1]) );
    }
  }
}
