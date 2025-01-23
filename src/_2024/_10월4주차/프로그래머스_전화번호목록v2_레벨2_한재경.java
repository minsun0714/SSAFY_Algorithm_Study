import java.util.*;
class 프로그래머스_전화번호목록v2_레벨2_한재경 {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        String now = "aa"; //현재 접두어
        for (int i = 0; i < phone_book.length; i++) { //현재 전화번호
            if (now.length() <= phone_book[i].length()) {
                if (now.equals(phone_book[i].substring(0, now.length()))) { //현재접두어까지 같으면
                    return false;
                } 
            }
            now = phone_book[i];
        }
        return true;
    }
}
