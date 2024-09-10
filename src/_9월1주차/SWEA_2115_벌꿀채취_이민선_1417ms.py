# 시뮬레이션, 백트래킹

T = int(input())


def get_max_honey(container, selected, depth, start):
    if sum(selected) > c:
        return 0

    max_honey = sum([x ** 2 for x in selected])

    if depth == m:
        return max_honey

    for i in range(start, m):
        max_honey = max(max_honey, get_max_honey(container, selected + [container[i]], depth + 1, i + 1))

    return max_honey


def select_container(selected, depth, start_x, start_y):
    global result
    if depth == 2:
        (x1, y1), (x2, y2) = selected

        container_a = board[x1][y1 - m + 1:y1 + 1]
        container_b = board[x2][y2 - m + 1:y2 + 1]

        sum_a = get_max_honey(container_a, [], 0, 0)
        sum_b = get_max_honey(container_b, [], 0, 0)

        result = max(result, sum_a + sum_b)

        return
    for i in range(start_x, n):
        for j in range(start_y, n):
            if depth == 1 and selected[0][0] == i and j < selected[0][1] + m:
                continue
            select_container(selected + [(i, j)], depth + 1, i if j + m <= n - 1 else i + 1,
                             j + m if depth == 1 and selected[0][0] == i else m - 1)


answer = ""
for t in range(1, T + 1):
    n, m, c = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(n)]
    result = 0
    select_container([], 0, 0, m - 1)
    answer += f"#{t} {result}\n"
print(answer)