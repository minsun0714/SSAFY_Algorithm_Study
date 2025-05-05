import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Deque<Car> a = new LinkedList<>();
        Deque<Car> b = new LinkedList<>();
        Deque<Car> c = new LinkedList<>();
        Deque<Car> d = new LinkedList<>();
        
//입력 받기
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Car car = new Car(i, Integer.parseInt(st.nextToken()));
            String dir = st.nextToken();
            if(dir.equals("A")){
                a.addLast(car);
            }else if(dir.equals("B")){
                b.addLast(car);
            }else if(dir.equals("C")){
                c.addLast(car);
            }else if(dir.equals("D")){
                d.addLast(car);
            }
        }
//다 나가거나 교착상태될때까지 반복
        List<int[]> result = new ArrayList<>();
        int moved = 0;
        int time = 0;
        while(moved<N){
            boolean cur_a = false;
            boolean cur_b = false;
            boolean cur_c = false;
            boolean cur_d = false;
            int cur_moved = 0;
          //차량 유무 체크
            if(!a.isEmpty() && time>=a.peekFirst().arr){
                cur_a=true;
            }
            if(!b.isEmpty() && time>=b.peekFirst().arr){
                cur_b=true;
            }
            if(!c.isEmpty() && time>=c.peekFirst().arr){
                cur_c=true;
            }
            if(!d.isEmpty() && time>=d.peekFirst().arr){
                cur_d=true;
            }
          //차량 나갈 수 있는지 체크
            if(cur_a && !cur_d){
                result.add(new int[] {time, a.removeFirst().order});
                cur_moved++;
            }
            if(cur_b && !cur_a){
                result.add(new int[] {time, b.removeFirst().order});
                cur_moved++;
            }
            if(cur_c && !cur_b){
                result.add(new int[] {time, c.removeFirst().order});
                cur_moved++;
            }
            if(cur_d && !cur_c){
                result.add(new int[] {time, d.removeFirst().order});
                cur_moved++;
            }
          //교착상태
            if(cur_moved==0&&cur_a&&cur_b&&cur_c&&cur_d){
                while(!a.isEmpty()){
                    result.add(new int[] {-1, a.removeFirst().order});
                }
                while(!b.isEmpty()){
                    result.add(new int[] {-1, b.removeFirst().order});
                }
                while(!c.isEmpty()){
                    result.add(new int[] {-1, c.removeFirst().order});
                }
                while(!d.isEmpty()){
                    result.add(new int[] {-1, d.removeFirst().order});
                }
                break;
            }
          //시간 스킵
            if (cur_moved == 0) {
                int nextTime = Integer.MAX_VALUE;
                if (!a.isEmpty()) nextTime = Math.min(nextTime, a.peekFirst().arr);
                if (!b.isEmpty()) nextTime = Math.min(nextTime, b.peekFirst().arr);
                if (!c.isEmpty()) nextTime = Math.min(nextTime, c.peekFirst().arr);
                if (!d.isEmpty()) nextTime = Math.min(nextTime, d.peekFirst().arr);
                
                // time을 불필요하게 넘기지 않도록 체크
                if (nextTime > time) {
                    time = nextTime;
                    continue;
                }
            }
            
            time++;
            moved+=cur_moved;
        }
        StringBuilder sb = new StringBuilder();
        result.sort((first, second)->Integer.compare(first[1], second[1]));
        for(int i = 0; i<N; i++){
            sb.append(result.get(i)[0]).append("\n");
        }
        System.out.print(sb);
    }
    static class Car{
        int order;
        int arr;
        public Car(int order, int arr){
            this.order = order;
            this.arr = arr;
        }
    }
}
