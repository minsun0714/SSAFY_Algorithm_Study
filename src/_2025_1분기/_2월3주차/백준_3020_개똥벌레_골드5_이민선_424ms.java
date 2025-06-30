package _2월3주차;

import java.io.*;
import java.util.*;

public class 백준_3020_개똥벌레_골드5_이민선_424ms {
  static int n, h;
  static int[] bottom;
  static int[] up;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    h = Integer.parseInt(st.nextToken());

    bottom = new int[n / 2];
    up = new int[n / 2];

    for (int i=0;i<n;i++){
      int num = Integer.parseInt(br.readLine());
      if (i % 2 == 0) bottom[i / 2] = num;
      else up[i / 2] = num;

    }
    Arrays.sort(bottom);
    Arrays.sort(up);
    int minVal = 200_001;
    int minValCount = 0;
    for (int i=0;i<h;i++){
      int target = h - i;
      int countBottom = binarySearch(bottom, target);
      target = i + 1;
      int countUp = binarySearch(up, target);
      if (minVal > n - countUp - countBottom){
        minVal = n - countUp - countBottom;
        minValCount = 1;
      } else if (minVal == n - countUp - countBottom){
        minValCount++;
      }
    }
    System.out.println(minVal + " " + minValCount);
  }

  public static int binarySearch(int[] arr, int target){
    int s = 0, e = n / 2 - 1;

    while (s <= e){
      int mid = (s + e) / 2;

      if (arr[mid] < target){
        s = mid + 1;
      } else {
        e = mid - 1;
      }
    }
    return s;
  }
}
