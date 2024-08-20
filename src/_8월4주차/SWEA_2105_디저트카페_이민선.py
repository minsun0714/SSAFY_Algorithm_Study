# 391ms
# 구현
T = int(input())

def add_dot(line, idx, x, y):
    for _ in range(1, line + 1):
        x, y = x + dx[idx], y + dy[idx]
        s.add(board[x][y])
    return x, y


for t in range(1, T + 1):
    n = int(input())

    board = [list(map(int, input().split())) for _ in range(n)]

    dx = [1, 1, -1, -1]
    dy = [-1, 1, 1, -1]

    answer = -1
    for x in range(n):
        for y in range(n):
            for first_line in range(1, y + 1):
                for second_line in range(1, n - x - first_line):
                    if y + second_line >= n or first_line + x  >= n:
                        continue
                    s = set()
                    x, y = add_dot(first_line, 0, x, y)
                    x, y = add_dot(second_line, 1, x, y)
                    x, y = add_dot(first_line, 2, x, y)
                    x, y = add_dot(second_line, 3, x, y)
                    if len(s) < 2 * (first_line + second_line):
                        continue
                    answer = max(answer, len(s))
    print(f"#{t} {answer}")