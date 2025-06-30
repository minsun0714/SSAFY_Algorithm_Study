import java.util.*;

//구현
public class 프로그래머스_붕대감기_레벨2_한재경 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0]; // 연속 시전 시간
        int x = bandage[1]; // 초당 회복량
        int y = bandage[2]; // t초 연속 성공 시 추가 회복량
        int maxHealth = health;
        int curHealth = health;
        int cons = 0; // 현재까지 끊기지 않고 시전한 연속 시간
        int attackIdx = 0; // 다음 처리할 공격 인덱스
        int nAttacks = attacks.length;
        int lastTime = attacks[nAttacks - 1][0];
        
        // 시간 1초부터 마지막 공격 시간까지 시뮬레이션
        for (int time = 1; time <= lastTime; time++) {
            // 이 순간 공격이 있는지 확인
            if (attackIdx < nAttacks && attacks[attackIdx][0] == time) {
                // 공격이 들어오면 즉시 회복 취소 및 연속 카운트 초기화
                cons = 0;
                curHealth -= attacks[attackIdx][1];
                if (curHealth <= 0) {
                    return -1;
                }
                attackIdx++;
            } else {
                // 공격이 없으면 1초간 붕대 감기 성공 → 회복
                curHealth = Math.min(curHealth + x, maxHealth);
                cons++;
                // t초 연속 시전 성공 시 추가 회복 후 연속 카운트 초기화
                if (cons == t) {
                    curHealth = Math.min(curHealth + y, maxHealth);
                    cons = 0;
                }
            }
        }
        
        return curHealth;
    }
}
