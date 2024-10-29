import java.util.*;
class Song implements Comparable<Song> {
    int idx;
    int plays;
    public Song(int idx, int plays) {
        this.idx = idx;
        this.plays = plays;
    }
    @Override
    public int compareTo(Song o) {
        return o.plays - this.plays;
    }
    @Override
    public String toString() {
        return "" + idx + " " + plays;
    }
}
class 프로그래머스_베스트앨범_레벨3_한재경 {
    public int[] solution(String[] genres, int[] plays) { //i번노래 장르, i번노래 재생수
        Map<String, List<Song>> songs = new HashMap<>(); //장르-각노래 map
        Map<String, Integer> cnts = new HashMap<>(); //장르-재생수cnt map
        for (int i = 0; i < genres.length; i++) {
            songs.putIfAbsent(genres[i], new ArrayList<>());
            songs.get(genres[i]).add(new Song(i, plays[i]));
            cnts.put(genres[i], cnts.getOrDefault(genres[i], 0) + plays[i]); //재생 수 카운팅
        }
        
        //songs 정렬(내림차순)
        for (Map.Entry<String, List<Song>> entry : songs.entrySet()) {
            Collections.sort(entry.getValue());
        }
        
        //cnts 순 장르 리턴 - value 기준 내림차순 정렬
        List<String> keySet = new ArrayList<>(cnts.keySet()); //value기준 내림차 정렬된 장르 목록
        keySet.sort((o1, o2) -> cnts.get(o2).compareTo(cnts.get(o1)));
        
        //정렬된 keySet에 맞는 songs 2개씩 result에 넣기
        List<Integer> result = new ArrayList<>();
        for (String genre : keySet) {
            int i = 0;
            for (Song s : songs.get(genre)) {
                result.add(s.idx);
                i++;
                if (i >= 2) {
                    break;
                }
            }
        }
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
