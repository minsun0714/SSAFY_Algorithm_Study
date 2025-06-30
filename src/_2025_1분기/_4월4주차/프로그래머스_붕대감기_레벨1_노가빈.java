//반복문 이용한 구현현
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int max_health = health;
        int during_skill_time = attacks[0][0] -1;
        
        int max_skill_time = bandage[0];
        int recovery_amount = bandage[1];
        int bonus_recovery_amount = bandage[2];
        
        
        for(int i = 0; i < attacks.length; i++){
            
            int attack_time = attacks[i][0];
            int attack_amount = attacks[i][1];
            
            if(i != 0){
                during_skill_time = attack_time - attacks[i-1][0] -1;    
            }
            
            if(during_skill_time < 0) during_skill_time = 0;
            // System.out.println("attack_time : " + attack_time + ", attack_amount : " + attack_amount + ", during_skill_time : " + during_skill_time + ", health : " + health);
            
            
            health += during_skill_time * recovery_amount;
            
            health += (during_skill_time / max_skill_time) * bonus_recovery_amount;
            during_skill_time = 0;
            
            if(health > max_health) health = max_health;
            // System.out.println("공격 전 health : " + health + "\n");
            health -= attack_amount;
            // System.out.println("공격 후 health : " + health + "\n");
            if(health < 1) return -1;
            
            
            
        }
        
        return health;
    }
}