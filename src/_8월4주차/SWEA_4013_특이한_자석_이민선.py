from collections import deque

T = int(input())

def turn_clockwise(deque):
    last = deque.pop()
    deque.appendleft(last)
    
def turn_counterclockwise(deque):
    first = deque.popleft()
    deque.append(first)

for t in range(1, T + 1):
    k = int(input())
    
    deques = []
    for _ in range(4):
        deques.append(deque(map(int, input().split())))
    
    for _ in range(k):
        idx, dir = map(int, input().split())
        
        wheels_to_rotate = []
        temp_dir = dir
        for j in range(idx, 4):
            print(deques[j][6] == deques[j - 1][2])
            temp_dir *= -1
            if deques[j][6] == deques[j - 1][2]:
                wheels_to_rotate.append((j, temp_dir))

        print(wheels_to_rotate)