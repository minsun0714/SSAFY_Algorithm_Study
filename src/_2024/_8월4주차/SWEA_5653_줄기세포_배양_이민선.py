# 11549ms
# 시뮬레이션
import heapq
T = int(input())

for t in range(1, T + 1):
    n, m, k = map(int, input().split())

    board = [[[0] * 2 for _ in range(600 + m)] for _ in range(600 + n)]

    for i in range(300, 300 + n):
        row = list(map(int, input().split()))
        for j in range(300, 300 + m):
            board[i][j][0] = row[j - 300]
            board[i][j][1] = -row[j - 300]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    hq = []
    heapq.heapify(hq)

    h = 0
    while h < k:
        h += 1

        while hq:
            val, x, y = heapq.heappop(hq)

            if board[x][y][0] == 0:
                board[x][y][0] = -val
                board[x][y][1] = val - 1

        if h == k:
            break

        for i in range(300 - h, 300 + n + h):
            for j in range(300- h, 300 + m + h):
                if board[i][j][1] == -1:
                    board[i][j][1] = board[i][j][0]
                    for d in range(4):
                        nx = i + dx[d]
                        ny = j + dy[d]

                        if board[nx][ny][0] == 0:
                            heapq.heappush(hq, (-board[i][j][0], nx, ny))

                if board[i][j][1] > 0:
                    board[i][j][1] -= 1
                elif board[i][j][1] < 0:
                    board[i][j][1] += 1


    answer = 0
    for i in range(600 + n):
        for j in range(600 + m):
            if board[i][j][1]:
                answer += 1
    print(f"#{t} {answer}")