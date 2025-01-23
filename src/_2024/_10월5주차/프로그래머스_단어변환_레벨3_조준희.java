import java.util.*;

class 프로그래머스_단어변환_레벨3_조준희 {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        
        Queue<Word> q = new LinkedList<>();
        
        q.add(new Word(begin, 0));
        while(!q.isEmpty()){
            Word cur = q.poll();
            
            if(cur.w.equals(target))
                return cur.d;
            
            for(int idx = 0; idx<words.length; idx++){
                int diff = 0;
                for(int i = 0; i<words[idx].length(); i++){
                    if(cur.w.charAt(i)!=words[idx].charAt(i)){
                        diff++;
                    }
                }
                
                if(diff==1 && !visited[idx]){
                    visited[idx]=true;
                    q.add(new Word(words[idx], cur.d+1));
                }
            }
        }
        return answer;
    }
    static class Word{
        String w;
        int d;
        public Word(String w, int d){
            this.w = w;
            this.d = d;
        }
    }
}