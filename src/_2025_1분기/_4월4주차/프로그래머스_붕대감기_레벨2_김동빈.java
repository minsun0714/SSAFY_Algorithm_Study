package etc._4_4;

public class 프로그래머스_붕대감기_레벨2_김동빈 {
	// 반복문
	static class Solution {
	    public int solution(int[] bandage, int health, int[][] attacks) {
	        int t = bandage[0]; // 시전 시간
	        int x = bandage[1]; // 초당 회복량
	        int y = bandage[2]; // 추가 회복량
	        
	        int currentHealth = health;
	        int lastTime = 0; // 마지막 공격 시간
	        
	        // 공격
	        for (int i = 0; i < attacks.length; i++) {
	        	
	            int attackTime = attacks[i][0];
	            int damage = attacks[i][1];
	            
	            // 이전에 얼마나 회복했을까?
	            int fullTime = attackTime - lastTime - 1;
	            int healCount = fullTime / t;
	            int healAmount = fullTime * x + healCount * y; 
	            currentHealth = Math.min(currentHealth + healAmount, health);
	            
	            // 공격
	            currentHealth -= damage;
	            if (currentHealth <= 0) {
	                return -1; // 체력 0
	            }
	            
	            // 마지막 공격
	            lastTime = attackTime;
	        }
	        
	        return currentHealth;
	    }
	}
}
