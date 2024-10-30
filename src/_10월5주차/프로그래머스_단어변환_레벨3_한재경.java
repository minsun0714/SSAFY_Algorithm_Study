import java.util.*;
class WordAndIdx {
    String word;
    int cnt;
    public WordAndIdx(String word, int cnt) {
        this.word = word;
        this.cnt = cnt;
    }
}
class 프로그래머스_단어변환_레벨3_한재경 {
    public int bfs(String begin, String target, String[] words) {
        Queue<WordAndIdx> q = new ArrayDeque<>();
        q.add(new WordAndIdx(begin, 0));
        int[] visited = new int[words.length]; //방문한 words 인덱스
        while(!q.isEmpty()) {
            WordAndIdx wai = q.poll();
            String now = wai.word;
            int cnt = wai.cnt;
            if (now.equals(target)) {
                return cnt;
            }
            for (int i = 0; i < words.length; i++) { //i번째 단어
                if (visited[i] == 0) {
                    //now랑 한글자만 다르면 큐에 넣기
                    int diff = 0;
                    for (int j = 0; j < now.length(); j++) {
                        if (now.charAt(j) != words[i].charAt(j)) {
                            diff++;
                        }
                    }
                    if (diff == 1) {
                        q.add(new WordAndIdx(words[i], cnt + 1));
                        visited[i] = 1;
                    }
                }
            }
        }
        return 0;
    }
    public int solution(String begin, String target, String[] words) {
        
        return bfs(begin, target, words);
    }
}
