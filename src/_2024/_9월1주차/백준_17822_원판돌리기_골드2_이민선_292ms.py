from collections import deque


def bfs(x, y):
    q = deque([(x, y)])

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx, ny = x + dx[i], (y + dy[i]) % m

            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue

            if visited[nx][ny] > 0:
                continue

            if circles[x + 1][y] != circles[nx + 1][ny]:
                continue

            visited[x][y] = 1
            visited[nx][ny] = 1

            global has_erase
            has_erase = True
            q.append((nx, ny))


n, m, t = map(int, input().split())

circles = [deque() for _ in range(n + 1)]

for i in range(1, n + 1):
    circles[i] = deque(list(map(int, input().split())))

visited = [deque([0] * m) for _ in range(n)]

for _ in range(t):
    x, d, k = map(int, input().split())

    for i in range(1, n + 1):
        if i % x == 0:
            circles[i].rotate(k if d == 0 else -k)
            visited[i - 1].rotate(k if d == 0 else -k)

    has_erase = False

    for i in range(n):
        for j in range(m):
            if not visited[i][j]:
                bfs(i, j)

    if has_erase:
        for i in range(1, n + 1):
            for j in range(m):
                if visited[i - 1][j]:
                    circles[i][j] = 0
    else:
        sum_val = sum(sum(row) for row in circles)
        count = n * m - sum([row.count(0) for row in circles])

        if count == 0:
            break
        avg = sum_val / count

        for i in range(1, n + 1):
            for j in range(m):
                if circles[i][j] > 0:
                    if circles[i][j] > avg:
                        circles[i][j] -= 1
                    elif circles[i][j] < avg:
                        circles[i][j] += 1

answer = 0
for row in circles:
    answer += sum(row)
print(answer)