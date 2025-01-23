r, c, t = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(r)]

# 공기청정기 row 인덱스 파악
upper_r, lower_r = -1, -1
for j in range(r):
    if board[j][0] == -1:
        if upper_r == -1:
            upper_r = j
        else:
            lower_r = j

def spread():
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    # 확산이 일어날 칸 인덱스 모두 저장했다가 한 번에 확산
    adj_list = []

    for x in range(r):
        for y in range(c):
            if y == 0 and (x == upper_r or x == lower_r):
                continue

            cur_adj = []
            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]

                if nx < 0 or nx >= r or ny < 0 or ny >= c:
                    continue
                if board[nx][ny] == -1:
                    continue

                cur_adj.append((nx, ny))

            total_spread = 0
            for i in range(len(cur_adj)):
                ax, ay = cur_adj[i]
                plus_amount = board[x][y] // 5
                total_spread += plus_amount
                adj_list.append((ax, ay, plus_amount))

            board[x][y] -= total_spread

    for x, y, amount in adj_list:
        board[x][y] += amount

def rotate_counter_clockwise():
    # 공기청정기부터 위로 올라가면서 원소 이동
    for j in range(upper_r - 1, 0, -1):
        board[j][0] = board[j - 1][0]
    board[0][:c - 1] = board[0][1:c]
    for j in range(0, upper_r):
        board[j][c - 1] = board[j + 1][c - 1]
    board[upper_r][2:] = board[upper_r][1: c - 1]
    board[upper_r][1] = 0

def rotate_clockwise():
    #공기청정기부터 아래로 내려가면서 원소 이동
    for j in range(lower_r + 1, r - 1):
        board[j][0] = board[j + 1][0]
    board[r - 1][:c - 1] = board[r - 1][1:c]
    for j in range(r - 1, lower_r, -1):
        board[j][c - 1] = board[j - 1][c - 1]
    board[lower_r][2:] = board[lower_r][1:c - 1]
    board[lower_r][1] = 0


while t:
    t -= 1

    # 미세먼지 확산
    spread()

    # 위쪽 부분 반시계 방향 순환
    rotate_counter_clockwise()

    # 아래쪽 부분 시계 방향 순환
    rotate_clockwise()

# 공기청정기 -1을 두번 뻄을 고려하여 다시 2를 더해줌
print(sum([sum(row) for row in board]) + 2)