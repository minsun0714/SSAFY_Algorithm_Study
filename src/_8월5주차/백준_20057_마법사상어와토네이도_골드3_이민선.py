# n = int(input())
#
# board = [list(map(int, input().split())) for _ in range(n)]
#
# new_proportion = list(reversed(list(zip(*board))))
#
# print(new_proportion)

import math
n = int(input())

board = [list(map(int, input().split())) for _ in range(n)]

dr = [0, 1, 0, -1]
dc = [-1, 0, 1, 0]


def put_sand(r, c, p, y):
    global answer
    if n > r >= 0 and n > c >= 0:
        board[r][c] += math.floor(p * y)
    else:
        answer += math.floor(p * y)
    return math.floor(p * y)


def blow(xr, xc):
    d = 0
    count = 1
    while xr >= 0 and xc >= 0:
        for i in range(2):
            for j in range(count):
                yr, yc = xr + dr[d % 4], xc + dc[d % 4]
                ar, ac = yr + dr[d % 4], yc + dc[d % 4]

                if yr == 0 and yc == 0:
                    return
                y = board[yr][yc]

                move = 0

                one_r_right, one_c_right = xr + dr[(d - 1) % 4], xc + dc[(d - 1) % 4]
                one_r_left, one_c_left = xr + dr[(d + 1) % 4], xc + dc[(d + 1) % 4]
                move += put_sand(one_r_right, one_c_right, 0.01, y)
                move += put_sand(one_r_left, one_c_left, 0.01, y)
                seven_r_right, seven_c_right = yr + dr[(d - 1) % 4], yc + dc[(d - 1) % 4]
                seven_r_left, seven_c_left = yr + dr[(d + 1) % 4], yc + dc[(d + 1) % 4]
                move += put_sand(seven_r_right, seven_c_right, 0.07, y)
                move += put_sand(seven_r_left, seven_c_left, 0.07, y)
                two_r_right, two_c_right = yr + dr[(d - 1) % 4] * 2, yc + dc[(d - 1) % 4]
                two_r_left, two_c_left = yr + dr[(d + 1) % 4] * 2, yc + dc[(d + 1) % 4]
                move += put_sand(two_r_right, two_c_right, 0.02, y)
                move += put_sand(two_r_left, two_c_left, 0.02, y)
                ten_r_right, ten_c_right = ar + dr[(d - 1) % 4], ac + dc[(d - 1) % 4]
                ten_r_left, ten_c_left = ar + dr[(d + 1) % 4], ac + dc[(d + 1) % 4]
                move += put_sand(ten_r_right, ten_c_right, 0.1, y)
                move += put_sand(ten_r_left, ten_c_left, 0.1, y)
                five_r, five_c = ar + dr[d % 4], ac + dc[d % 4]
                move += put_sand(five_r, five_c, 0.05, y)

                put_sand(ar, ac, 1, y - move)

                board[yr][yc] = 0
                xr, xc = yr, yc

            d += 1
        count += 1


answer = 0
mid = n // 2
blow(mid, mid)
print(answer)