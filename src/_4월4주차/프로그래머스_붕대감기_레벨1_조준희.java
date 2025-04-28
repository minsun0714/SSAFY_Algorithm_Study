class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0];  
        int x = bandage[1];  
        int y = bandage[2];  
        
        int currentHealth = health;
        int lastAttackTime = 0;  
        
        for (int i = 0; i < attacks.length; i++) {
            int attackTime = attacks[i][0];  
            int damage = attacks[i][1];   
            
            if (i == 0) {
                currentHealth -= damage;
            } else {
                int timeDiff = attackTime - lastAttackTime - 1;
                
                int recovery = (timeDiff / t) * y + timeDiff * x;
                currentHealth += recovery;
                
                currentHealth = Math.min(currentHealth, health);
                currentHealth -= damage;
            }
            
            if (currentHealth <= 0) {
                return -1;
            }
            
            lastAttackTime = attackTime;
        }
        
        return currentHealth;
    }
}
