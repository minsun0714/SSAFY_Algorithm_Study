# 928ms
# 구현
import copy

n, m, k = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(n)]

arr = [list(map(int, input().split())) for _ in range(k)]

def rotate(copied_board, r,c,s):
    for k in range(s):
        temp = copied_board[r - s - 1 + k][c - s - 1 + k]
        for i in range(r - s - 1 + k, r + s - 1 - k):
            copied_board[i][c - s - 1 + k] = copied_board[i + 1][c - s - 1 + k]
        for j in range(c - s - 1 + k, c + s - 1 - k):
            copied_board[r + s - 1 - k][j] = copied_board[r + s - 1 - k][j + 1]
        for i in range(r + s - 1 - k, r - s - 1 + k, -1):
            copied_board[i][c + s - 1 - k] = copied_board[i - 1][c + s - 1 - k]
        for j in range(c + s - 1 - k, c - s - 1 + k, -1):
            copied_board[r - s - 1 + k][j] = copied_board[r - s - 1 + k][j - 1]
        copied_board[r - s - 1 + k][c - s + k] = temp

def back_tracking(selected, depth):
    if depth == k:
        copied_board = copy.deepcopy(board)
        for r, c, s in selected:
            rotate(copied_board, r,c, s)
        for row in copied_board:
            global answer
            answer = min(answer, sum(row))
        return
    for i in range(k):
        if visited[i]:
            continue
        visited[i] = 1
        back_tracking(selected + [arr[i]], depth + 1)
        visited[i] = 0

answer = 1e9
visited = [0] * k
back_tracking([], 0)

print(answer)