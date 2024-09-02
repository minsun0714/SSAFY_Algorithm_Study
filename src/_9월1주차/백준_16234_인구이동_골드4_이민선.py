# 6860ms
# DFS

from collections import defaultdict

n, l, r = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(n)]
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def dfs(x, y):
    stack = [(x, y)]

    while stack:
        x, y = stack.pop()

        visited[x][y] = group_idx

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue

            if not (l <= abs(board[x][y] - board[nx][ny]) <= r):
                continue

            if visited[nx][ny]:
                continue

            stack.append((nx, ny))


answer = 0


while True:
    # 이중 for문으로 돌면서 visited 배열에 연합 번호 체크
    visited = [[0] * n for _ in range(n)]
    group_idx = 1

    for i in range(n):
        for j in range(n):
            if visited[i][j] == 0:
                dfs(i, j)
                group_idx += 1

    if group_idx == n ** 2 + 1:
        break

    # 각 연합의 합산 인구수, 나라 수 구하고
    union_sum = defaultdict(int)
    union_count = defaultdict(int)

    for i in range(n):
        for j in range(n):
            union_sum[visited[i][j]] += board[i][j]
            union_count[visited[i][j]] += 1

    # 원본 배열에 덮어씀
    for i in range(n):
        for j in range(n):
            board[i][j] = union_sum[visited[i][j]] // union_count[visited[i][j]]
    answer += 1

print(answer)