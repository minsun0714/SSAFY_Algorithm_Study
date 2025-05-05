
class 프로그래머스_붕대감기_레벨1_이민선 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int time = 0;
        int idx = 0;
        int successCount = 0;
        while (health > 0 && idx < attacks.length){
            if (attacks[idx][0] == ++time){
                health -= attacks[idx][1];
                successCount = 0;
                idx++;
            } else {
                if (health + bandage[1] <= maxHealth){
                    health += bandage[1];
                    successCount++;
                    if (successCount == bandage[0]){
                        successCount = 0;
                        if (health + bandage[2] <= maxHealth){
                            health += bandage[2];
                        } else health = maxHealth;
                    }
                } else health = maxHealth;
            }
        }
        return health <= 0 ? -1 : health;
    }
}