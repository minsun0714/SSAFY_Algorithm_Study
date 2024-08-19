
# 작업중
import copy

n, m, d = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(n)]


def bfs(selected, r, copied_board):
    catch_count = [0] * 3
    catch = []
    for a in range(3):
        stack = [(n - r, selected[a], 1)]

        dx = [0, 0, -1]
        dy = [-1, 1, 0]

        visited = [[0] * m for _ in range(n - r)]

        while stack:
            x, y, depth = stack.pop()

            if depth > d:
                continue
            if catch_count[a]:
                continue

            for i in range(3):
                nx, ny = x + dx[i], y + dy[i]

                if nx < 0 or nx >= n - r or ny < 0 or ny >= m:
                    continue

                if visited[nx][ny]:
                    continue

                visited[nx][ny] = depth

                if copied_board[nx][ny] == 0:
                    stack.append((nx, ny, depth + 1))
                else:
                    copied_board[nx][ny] = 0
                    catch_count[a] = 1
                    catch.append((nx, ny))
                    break
    copied_board.pop()
    copied_board.pop()
    return sum(catch_count)


def back_tracking(selected, depth, start):
    if depth == 3:
        copied_board = copy.deepcopy(board)
        result = 0
        for r in range(n):
            copied_board.append([0] * m)
            catch_count = bfs(selected, r, copied_board)
            result += catch_count
        global answer
        answer = max(answer, result)
        return
    for i in range(start, m):
        back_tracking(selected + [i], depth + 1, i + 1)


answer = 0
back_tracking([], 0, 0)
print(answer)