package etc._6_2;

import java.io.*;
import java.util.*;

// 유니온 파인드
public class 백준_1765_닭싸움팀정하기_골드2_김동빈_200ms {
	
    static int[] parents;
    static ArrayList<Integer>[] enemies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        enemies = new ArrayList[N + 1];
        
        for(int i = 1; i <= N; i++) {
            parents[i] = i;
            enemies[i] = new ArrayList<Integer>();
        }

        int M = Integer.parseInt(br.readLine());

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(op.equals("F")) {
                union(x, y);
            } else if (op.equals("E")) {
            	enemies[x].add(y);
            	enemies[y].add(x);
            }
        }
        
        for(int i = 1; i <= N; i++) {
        	for (int Ae : enemies[i]) {
        		union(i, enemies[Ae].get(0));
        	}
        }

        Set<Integer> set = new HashSet<>();
        
        for(int i = 1; i <= N; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }
    
    static int find(int X) {
		
		int parentX = parents[X];
		if (X == parentX) return X;
		
		return parents[parentX] = find(parentX);
	}
	
	static boolean union(int A, int B) {
		
		int parentA = find(A);
		int parentB = find(B);
		
		if (parentA == parentB) return false;
		
		parents[parentA] = parentB;
		return true;
	}
}