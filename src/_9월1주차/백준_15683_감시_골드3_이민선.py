# 1144ms
# 구현, 시뮬레이션
n, m = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(n)]

dx_list = [[[0], [1], [0], [-1]], [[0, 0], [-1, 1], [0, 0], [-1, 1]], [[-1, 0], [0, 1], [1, 0], [0, -1]], [[0, -1, 0],[-1, 0, 1],[0, 1, 0],[1, 0, -1]], [[-1, 0, 1, 0],[-1, 0, 1, 0],[-1, 0, 1, 0],[-1, 0, 1, 0]]]
dy_list = [[[1], [0], [-1], [0]], [[-1, 1], [0, 0], [-1, 1], [0, 0]], [[0, 1], [1, 0],[0, -1], [-1, 0]], [[-1, 0, 1],[0, 1, 0],[1, 0, -1],[0, -1, 0]], [[0, 1, 0, -1],[0, 1, 0, -1],[0, 1, 0, -1],[0, 1, 0, -1]]]

cctv = []

for i in range(n):
    for j in range(m):
        if 5 >= board[i][j] >= 1:
            cctv.append((i, j, board[i][j] - 1))

def put(info, d):
    x, y, idx = info
    dx = dx_list[idx][d]
    dy = dy_list[idx][d]
    distance = 1
    nx, ny = x, y
    put_list = []
    for i in range(len(dx)):
        while n > nx >= 0 and m > ny >= 0:
            nx, ny = nx + dx[i], ny + dy[i]
            distance += 1
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                break
            if board[nx][ny] == 0:
                board[nx][ny] = '#'
                put_list.append((nx, ny))
            elif board[nx][ny] == 6:
                break

        nx, ny = x, y
        distance = 1
    return put_list

def backtracking(selected, depth, start):
    global answer
    if depth == len(cctv):
        sum_val = sum([row.count(0) for row in board])
        answer = min(answer, sum_val)
        return

    for i in range(start, len(cctv)):
        for d in range(4):
            put_list = put(cctv[i], d)
            backtracking(selected + [d], depth + 1, i + 1)
            for x, y in put_list:
                board[x][y] = 0

answer = 1e9
backtracking([], 0, 0)
print(answer)