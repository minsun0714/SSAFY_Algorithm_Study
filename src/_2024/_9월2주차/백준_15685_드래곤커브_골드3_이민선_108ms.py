n = int(input())


def add_dragon_curve(x, y, d, g):
    dx = [1, 0, -1, 0]
    dy = [0, -1, 0, 1]

    prev_generation = [(x, y, d)]

    while g > -1:
        g -= 1

        cur_generation = []

        cur_x, cur_y, cur_d = prev_generation[-1]

        for i in range(len(prev_generation) - 1 if g >= 0 else 0, -1, -1):
            prev_x, prev_y, prev_d = prev_generation[i]

            cur_x += dx[cur_d]
            cur_y += dy[cur_d]
            cur_d = (prev_d + 1) % 4

            cur_generation.append((cur_x, cur_y, cur_d))

        prev_generation += cur_generation

    return prev_generation


s = set()

for _ in range(n):
    x, y, d, g = map(int, input().split())

    dots = add_dragon_curve(x, y, d, g)

    for dot in dots:
        s.add((dot[0], dot[1]))

answer = 0

for i in range(100):
    for j in range(100):
        if (i, j) in s and (i, j + 1) in s and (i + 1, j) in s and (i + 1, j + 1) in s:
            answer += 1

print(answer)