import java.util.*;
//브루트포스
class 프로그래머스_최소직사각형_레벨1_한재경 {
    public int solution(int[][] sizes) {
        int xMax = 0;
        int yMax = 0;
        for (int[] size : sizes) {
            xMax = Math.max(xMax, Math.max(size[0], size[1]));
            yMax = Math.max(yMax, Math.min(size[0], size[1]));
        }
        return xMax * yMax;
    }
}
