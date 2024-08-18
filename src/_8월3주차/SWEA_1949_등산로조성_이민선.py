import copy

T = int(input())

def dfs(x, y, c, depth, visited):
    global answer
    answer = max(answer, depth)

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    copied_visited = copy.deepcopy(visited)
    copied_visited[x][y] = 1

    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]

        if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
            if board[nx][ny] < board[x][y]:  # 이동할 곳이 더 낮다면
                dfs(nx, ny, c, depth + 1, copied_visited)
            elif c == 1 and board[nx][ny] - k < board[x][y]:  # 깎아서 이동할 수 있다면
                original = board[nx][ny]
                board[nx][ny] = board[x][y] - 1  # 깎기
                dfs(nx, ny, 0, depth + 1, copied_visited)  # 깎기 기회를 썼으므로 c를 0으로 설정
                board[nx][ny] = original  # 원래 상태로 복구


for t in range(1, T + 1):
    n, k = map(int, input().split())

    board = [list(map(int, input().split())) for _ in range(n)]

    max_val = max(map(max, board))

    visited = [[0] * n for _ in range(n)]
    answer = 0
    for i in range(n):
        for j in range(n):
            if board[i][j] == max_val:
                dfs(i, j, 1, 1, visited)  # 처음엔 깎을 기회가 1번 있음

    print(f"#{t} {answer}")