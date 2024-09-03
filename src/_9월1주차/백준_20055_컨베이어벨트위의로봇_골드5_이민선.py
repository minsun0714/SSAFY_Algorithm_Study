# PyPy 1540ms
# 구현, 시뮬레이션
from collections import deque

n, k = map(int, input().split())

belt = deque([[d, 0] for d in map(int, input().split())])


def robot_move(start, end):
    for i in range(start, end, -1):
        if belt[i][1] == 0 and belt[i][0] > 0 and belt[i - 1][1] == 1:
            belt[i][1] = 1
            belt[i][0] -= 1

            belt[i - 1][1] = 0


s = 0

while True:
    s += 1

    belt.rotate()
    belt[n - 1][1] = 0

    robot_move(n - 1, 0)

    belt[n - 1][1] = 0

    if belt[2 * n - 1][1] and belt[0][0]:
        belt[0][1] = 1
        belt[0][0] -= 1

        belt[2 * n - 1][1] = 0

    robot_move(2 * n - 1, n)

    if belt[0][0] > 0:
        belt[0][0] -= 1
        belt[0][1] = 1

    zero_durability = sum([1 for d, r in belt if d == 0])

    if zero_durability >= k:
        print(s)
        break



