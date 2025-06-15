import java.io.*;
import java.util.*;

// TreeMap
class Node {
    String name;
    Map<String, Node> nexts = new TreeMap<>();

    Node(String name) {
        this.name = name;
    }
}

public class 백준_7432_디스크트리_골드3_한재경_272ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<String, Node> heads = new TreeMap<>(); //최상위 디렉토리

        for (int i = 0; i < n; i++) {
            String[] s = (br.readLine()).split("\\\\");
            Node first; //첫 노드
            if (!heads.containsKey(s[0])) { //헤드목록에 없으면
                first = new Node(s[0]); //새로 추가
                heads.put(s[0], first);
            } else { //헤드 목록에 있으면
                first = heads.get(s[0]); //기존 헤드 사용
            }
            Node before = first;

            for (int j = 1; j < s.length; j++) {
                Node now;

                if (before.nexts.containsKey(s[j])) { //이미 연결된 디렉토리면
                    now = before.nexts.get(s[j]); //기존 객체 사용
                } else { //새 디렉토리면
                    now = new Node(s[j]);
                    before.nexts.put(s[j], now); //새로 추가
                }

                before = now; //before 갱신
            }
        }

        //헤드 돌면서 연결노드 쭉 출력
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Node> h : heads.entrySet()) {
            sb.append(h.getKey() + "\n");
            setNext(h.getValue().nexts, 1, sb);
        }
        System.out.println(sb);
    }
    public static void setNext(Map<String, Node> nows, int cnt, StringBuilder sb) {
        for (Map.Entry<String, Node> now : nows.entrySet()) {
            for (int i = 0; i < cnt; i++) {
                sb.append(" ");
            }
            sb.append(now.getKey() + "\n");
            setNext(now.getValue().nexts, cnt + 1, sb);
        }
    }
}
