import java.util.*;

//문자열, startsWith(접두문) 이용
class 프로그래머스_전화번호목록_레벨2_한재경 {
    public boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = i + 1; j < phone_book.length; j++) { //i+1요소
                if (phone_book[j].startsWith(phone_book[i])) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }
}
