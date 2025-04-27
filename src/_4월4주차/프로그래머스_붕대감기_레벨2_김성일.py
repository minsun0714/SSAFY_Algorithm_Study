def solution(bandage, health, attacks):
    maxHealth = health
    t = bandage[0]
    x = bandage[1]
    y = bandage[2]
    for i in range(len(attacks)-1):
        health -= attacks[i][1]
        if health <= 0:
            return -1
        else:
            heal_time = attacks[i+1][0] - attacks[i][0] - 1 
            bonus = heal_time//t 
            health += heal_time*x + bonus*y 
            if health > maxHealth: 
                health = maxHealth
    health -= attacks[-1][1]
    if health <= 0:
        return -1
    else:
        return health
