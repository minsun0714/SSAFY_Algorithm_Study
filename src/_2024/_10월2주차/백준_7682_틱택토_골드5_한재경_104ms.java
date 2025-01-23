import java.io.*;

//구현
public class 백준_7682_틱택토_골드5_한재경_104ms {
    static int n = 9;
    static String grid;

    //가로 세로 대각선에서 target이 빙고인지
    static boolean bingo(char target) {
        for (int i = 0; i < n; i += 3) { //가로
            if (grid.charAt(i) == target && grid.charAt(i + 1) == target && grid.charAt(i + 2) == target) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) { //세로
            if (grid.charAt(i) == target && grid.charAt(i + 3) == target && grid.charAt(i + 6) == target) {
                return true;
            }
        }
        if (grid.charAt(0) == target && grid.charAt(4) == target && grid.charAt(8) == target) {
            return true;
        }
        if (grid.charAt(2) == target && grid.charAt(4) == target && grid.charAt(6) == target) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            grid = br.readLine();

            if (grid.equals("end")) {
                break;
            }

            int xcnt = 0;
            int ocnt = 0;

            for (int i = 0; i < n; i++) {
                if (grid.charAt(i) == 'X') {
                    xcnt++;
                } else if (grid.charAt(i) == 'O') {
                    ocnt++;
                }
            }

            boolean xBingo = bingo('X');
            boolean oBingo = bingo('O');

            if (xBingo && oBingo) { //둘 다 승리
                System.out.println("invalid");
            }
            else if (xcnt == ocnt + 1 && (xBingo || xcnt == 5)) { //x승리 또는 꽉참
                if (oBingo) { //꽉차면 O승리 불가
                    System.out.println("invalid");
                } else {
                    System.out.println("valid");
                }
            } else if (xcnt == ocnt && oBingo) { //o승리
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }
}
