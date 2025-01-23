import java.io.*;
import java.util.*;

class 프로그래머스_완주하지못한선수_레벨1_한재경 {
    public String solution(String[] participant, String[] completion) {
        //1명 빼고 모두 마라톤 완주. 미완료자 이름 return
        //동명이인 가능
        Map<String, Integer> map = new HashMap<>(); //인원카운팅
        for (String p : participant) {
            if (map.containsKey(p)) { //이미 있음
                map.put(p, map.get(p) + 1);
            } else { //첫 추가
                map.put(p, 1);
            }
        }
        for (String c : completion) {
            map.put(c, map.get(c) - 1);
        }
        for (String k : map.keySet()) {
            if (map.get(k) != 0) {
                return k;
            }
        }
        return "";
    }
}
