# 181ms
# 시뮬레이션
from collections import deque

T = int(input())

def turn_clockwise(deq):
    last = deq.pop()
    deq.appendleft(last)
    
def turn_counterclockwise(deq):
    first = deq.popleft()
    deq.append(first)

for t in range(1, T + 1):
    k = int(input())
    
    deques = []
    for _ in range(4):
        deques.append(deque(map(int, input().split())))
    
    
    for _ in range(k):
        idx, dir = map(int, input().split())
        
        wheels_to_rotate = [0] * 4
        wheels_to_rotate[idx - 1] = dir

        temp_dir = dir
        for j in range(idx, 4):
            temp_dir *= -1
            if deques[j][6] != deques[j - 1][2]:
                wheels_to_rotate[j] = temp_dir
            else:
                break
        temp_dir = dir
        for j in range(idx - 1, 0, -1):
            temp_dir *= -1
            if deques[j][6] != deques[j - 1][2]:
                wheels_to_rotate[j - 1] = temp_dir
            else:
                break
        
        for j in range(4):
            if wheels_to_rotate[j] == 1:
                turn_clockwise(deques[j])
            elif wheels_to_rotate[j] == -1:
                turn_counterclockwise(deques[j])
        
    score = 0
    for i in range(4):
        score += deques[i][0] * 2 ** i
    print(f"#{t} {score}")
            