import java.io.*;
import java.util.*;

public class Softeer_성적평가_레벨3_김민섭_1480ms {

    // [MAIN]
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] array1 = new int[N + 1][3];
        int[][] array2 = new int[N + 1][3];
        int[][] array3 = new int[N + 1][3];
        int[][] array4 = new int[N + 1][3];
        array1[0][1] = 1001;
        array2[0][1] = 1001;
        array3[0][1] = 1001;
        array4[0][1] = 1000 * N + 1;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int n = 1; n <= N; n++) {
            array1[n][0] = n;
            array1[n][1] = Integer.parseInt(st.nextToken());
            array4[n][0] = n;
            array4[n][1] += array1[n][1];
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int n = 1; n <= N; n++) {
            array2[n][0] = n;
            array2[n][1] = Integer.parseInt(st.nextToken());
            array4[n][1] += array2[n][1];
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int n = 1; n <= N; n++) {
            array3[n][0] = n;
            array3[n][1] = Integer.parseInt(st.nextToken());
            array4[n][1] += array3[n][1];
        }

        // 1
        Arrays.sort(array1, (a, b) -> { return b[1] - a[1]; });
        for (int n = 1; n <= N; n++) {
            if (array1[n - 1][1] == array1[n][1]) {
                array1[n][2] = array1[n - 1][2];
                continue;
            }
            array1[n][2] = n;
        }
        Arrays.sort(array1, (a, b) -> { return a[0] - b[0]; });
        for (int n = 1; n <= N; n++) {
            sb.append(array1[n][2]).append(" ");
        }
        sb.append("\n");

        // 2
        Arrays.sort(array2, (a, b) -> { return b[1] - a[1]; });
        for (int n = 1; n <= N; n++) {
            if (array2[n - 1][1] == array2[n][1]) {
                array2[n][2] = array2[n - 1][2];
                continue;
            }
            array2[n][2] = n;
        }
        Arrays.sort(array2, (a, b) -> { return a[0] - b[0]; });
        for (int n = 1; n <= N; n++) {
            sb.append(array2[n][2]).append(" ");
        }
        sb.append("\n");

        Arrays.sort(array3, (a, b) -> { return b[1] - a[1]; });
        for (int n = 1; n <= N; n++) {
            if (array3[n - 1][1] == array3[n][1]) {
                array3[n][2] = array3[n - 1][2];
                continue;
            }
            array3[n][2] = n;
        }
        Arrays.sort(array3, (a, b) -> { return a[0] - b[0]; });
        for (int n = 1; n <= N; n++) {
            sb.append(array3[n][2]).append(" ");
        }
        sb.append("\n");

        Arrays.sort(array4, (a, b) -> { return b[1] - a[1]; });
        for (int n = 1; n <= N; n++) {
            if (array4[n - 1][1] == array4[n][1]) {
                array4[n][2] = array4[n - 1][2];
                continue;
            }
            array4[n][2] = n;
        }
        Arrays.sort(array4, (a, b) -> { return a[0] - b[0]; });
        for (int n = 1; n <= N; n++) {
            sb.append(array4[n][2]).append(" ");
        }

        System.out.println(sb);
    } // [MAIN]
    
}
