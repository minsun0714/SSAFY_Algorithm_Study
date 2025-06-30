import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{ 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String key = br.readLine();
        char[][] board = buildBoard(key);

        List<Character> text_pair = new ArrayList<>();
        
        for(int i = 0; i<text.length(); i++){
            char first = text.charAt(i);
            if(i==text.length()-1){
                text_pair.add(first);
                text_pair.add('X');

            }
            else if(first!=text.charAt(i+1)){
                text_pair.add(first);
                text_pair.add(text.charAt(i+1));
                i++;
            }
            else if(first==text.charAt(i+1) && first=='X'){
                text_pair.add(text.charAt(i));
                text_pair.add('Q');
            }
            else if(first==text.charAt(i+1)){
                text_pair.add(text.charAt(i));
                text_pair.add('X');
            }
        }

        Map<Character, Integer> boardIndexMap = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                char ch = board[i][j];
                boardIndexMap.put(ch, i * 5 + j);
            }
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text_pair.size(); i += 2) {
            char first = text_pair.get(i);
            char second = text_pair.get(i + 1);

            int idx1 = boardIndexMap.get(first);
            int idx2 = boardIndexMap.get(second);

            int row1 = idx1/5;
            int col1 = idx1%5;
            int row2 = idx2/5;
            int col2 = idx2 % 5;
        
            int new_idx1;
            int new_idx2;
            if (row1 == row2) {
                new_idx1 = row1 * 5 + (col1 + 1) % 5;
                new_idx2 = row2 * 5 + (col2 + 1) % 5;
            } else if (col1 == col2) {
                new_idx1 = ((row1 + 1) % 5) * 5 + col1;
                new_idx2 = ((row2 + 1) % 5) * 5 + col2;
            } else {
                new_idx1 = row1 * 5 + col2;
                new_idx2 = row2 * 5 + col1;
            }
        
            sb.append(board[new_idx1 / 5][new_idx1 % 5]);
            sb.append(board[new_idx2 / 5][new_idx2 % 5]);
        }
        System.out.println(sb);

    }
    public static char[][] buildBoard(String key) {
        key = key.toUpperCase(); 
        boolean[] used = new boolean[26]; 
        char[][] board = new char[5][5];
        int row = 0, col = 0;

        for (char ch: key.toCharArray()) {
            if (ch == 'J') ch = 'I';
            if (!used[ch - 'A']) {
                board[row][col++] = ch;
                used[ch - 'A'] = true;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') continue;
            if (!used[ch - 'A']) {
                board[row][col++] = ch;
                used[ch - 'A'] = true;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        return board;
    }
}
