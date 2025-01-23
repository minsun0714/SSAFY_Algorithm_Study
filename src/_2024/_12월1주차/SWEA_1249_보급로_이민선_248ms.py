import heapq

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())

    board = [list(map(int, list(input()))) for _ in range(n)]

    def dijkstra():
        dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
        hq = [(0, 0, 0)]
        heapq.heapify(hq)

        visited = [[0] * n for _ in range(n)]

        while hq:
            cost, x, y = heapq.heappop(hq)

            if x == n - 1 and y == n - 1:
                global answer
                answer = min(answer, cost)

            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                if nx < 0 or ny < 0 or nx >= n or ny >= n:
                    continue
                if visited[nx][ny]:
                    continue
                visited[nx][ny] = 1
                heapq.heappush(hq, (cost + board[nx][ny], nx, ny))
    answer = 1e9
    dijkstra()
    print(f"#{test_case} {answer}")
