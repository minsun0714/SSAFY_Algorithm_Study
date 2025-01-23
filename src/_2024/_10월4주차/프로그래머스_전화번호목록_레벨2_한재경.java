import java.util.*;

//문자열, startsWith(접두문) 이용
class 프로그래머스_전화번호목록_레벨2_한재경 {
    public boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }
        return true;
    }
}
