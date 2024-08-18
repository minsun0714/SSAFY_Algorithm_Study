# 3017ms
# 백트래킹, 시뮬레이션

import copy
from collections import deque

T = int(input())

for t in range(1, T + 1):
    n, w, h = map(int, input().split())

    def backtracking(selected, depth, board):
        if depth == n:
            total_left = 0
            for row in board:
                total_left += w - row.count(0)
            global answer
            answer = min(answer, total_left)
            return
        for i in range(w):
            copied_board = copy.deepcopy(board)
            shoot(i, board)
            backtracking(selected + [i], depth + 1, board)
            board = copied_board

    def shoot(s, board):
        highest = 0
        while highest < h - 1 and board[highest][s] == 0:
            highest += 1

        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        stack = [(board[highest][s], highest, s)]

        while stack:
            val, x, y = stack.pop()

            board[x][y] = 0

            for i in range(4):
                for d in range(val):
                    nx, ny = x + dx[i] * d, y + dy[i] * d

                    if nx < 0 or nx >= h or ny < 0 or ny >= w:
                        break
                    if board[nx][ny] == 0:
                        continue

                    stack.append((board[nx][ny], nx, ny))

        gravity(board)

    def gravity(board):
        for j in range(w):
            col = deque()
            for i in range(h):
                if board[i][j] != 0:
                    col.append(board[i][j])
            while len(col) < h:
                col.appendleft(0)
            for i in range(h):
                board[i][j] = col.popleft()


    board = [list(map(int, input().split())) for _ in range(h)]
    answer = 1e9
    backtracking([], 0, board)
    print(f"#{t} {answer}")