import java.util.*;

public class 프로그래머스_베스트앨범_3레벨_조준희 {
    static class Music{
        int idx;
        int played;
    
        public Music(int idx, int played){
            this.idx = idx;
            this.played = played;
        }
    }
    static class Genre{
        String title;
        int played;
    
        public Genre(String title, int played){
            this.title = title;
            this.played = played;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, List<Music>> musicMap = new HashMap<>();
        
        //키: 장르 값: 음악 리스트
        for(int i = 0; i<genres.length; i++){
            musicMap.putIfAbsent(genres[i], new ArrayList<>());
            musicMap.get(genres[i]).add(new Music(i, plays[i]));
        }
        
        //장르 타입(이름, 총 재생수) 배열
        Genre[] genreArr = new Genre[musicMap.keySet().size()];
        int idx=0;
        for(String genreTitle: musicMap.keySet()){
            int sum = 0;
            for(Music music: musicMap.get(genreTitle)){
                sum+=music.played;
            }
            genreArr[idx++]=new Genre(genreTitle, sum);
        }
        
        //장르 배열 정렬
        Arrays.sort(genreArr, (Genre o1, Genre o2)->{
            return o2.played-o1.played;
        });
        
        List<Integer> answerList = new ArrayList<>();
        
        //장르 순서대로 노래 뽑기
        for(Genre genre: genreArr){
            List<Music> musicList = musicMap.get(genre.title);
            musicList.sort((Music o1, Music o2)->{
                if(o1.played==o2.played){
                    return 1;
                }
                return o2.played-o1.played;
            });
            answerList.add(musicList.get(0).idx);
            if(musicList.size()>1)
                answerList.add(musicList.get(1).idx);
        }
        
        int[] answer = new int[answerList.size()];
        for(int i= 0; i<answerList.size(); i++){
            answer[i]=answerList.get(i);
        }
        return answer;
    }
	
}
