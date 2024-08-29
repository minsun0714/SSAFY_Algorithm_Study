# 2244ms
# 구현, 시뮬레이션
import math

n = int(input())

board = [list(map(int, input().split())) for _ in range(n)]

proportion = [
    (0, 0, 0.02, 0, 0),
    (0, 0.1, 0.07, 0.01, 0),
    (0.05, 1, 0, 0, 0),
    (0, 0.1, 0.07, 0.01, 0),
    (0, 0, 0.02, 0, 0)
]

def rotate():
    global proportion
    proportion = list(reversed(list(zip(*proportion))))

dr = [0, 1, 0, -1]
dc = [-1, 0, 1, 0]

start_sub = [(2, 3),(1, 2), (2, 1), (3, 2)]

def blow(xr, xc, yr, yc, d):
    global answer
    sr, sc = xr - start_sub[d][0], xc - start_sub[d][1]
    ar, ac = 0, 0
    y = board[yr][yc]

    sand_in = 0
    sand_out = 0
    # i, j는 board의 인덱스
    # i - sr, j - sc는 proportion의 인덱스
    for i in range(sr, sr + 5):
        for j in range(sc, sc + 5):
            p = proportion[i - sr][j - sc]
            sand_amount = math.floor(p * y)
            if p < 1 and (i < 0 or j < 0 or i >= n or j >= n):
                sand_out += sand_amount
                continue
            if p == 1:
                ar, ac = i, j

            else:
                sand_in += sand_amount
                board[i][j] += sand_amount
    if n > ar >= 0 and n > ac >= 0:
        board[ar][ac] += y - (sand_in + sand_out)
    # ar, ac가 범위 밖일 때 sand_out에 추가해야 함 (디버깅 가장 오래 걸린 부분)
    else:
        sand_out += y - (sand_in + sand_out)
    board[yr][yc] = 0

    answer += sand_out

def tornado(xr, xc):
    dir = 0
    c = 1

    while xc > -1:
        for _ in range(2):
            for _ in range(c):
                yr, yc = xr + dr[dir % 4], xc + dc[dir % 4]

                if xc == -1:
                    return

                blow(xr, xc, yr, yc, dir % 4)

                xr, xc = yr, yc
            dir += 1
            rotate()
        c += 1

answer = 0
tornado(n // 2, n // 2)
print(answer)