T = int(input())

for test_case in range(1, T + 1):
    n = int(input())

    locations = list(map(int, input().split()))
    locations = [(locations[i], locations[i + 1]) for i in range(0, 2 * n + 4, 2)]

    company = locations.pop(0)
    home = locations.pop(0)

    def dfs(selected, distance):
        global answer
        if len(selected) == n + 1:
            total_distance = distance + abs(home[0] - selected[-1][0]) + abs(home[1] - selected[-1][1])
            answer = min(answer, total_distance)
            return

        for i in range(len(locations)):
            if not visited[i]:
                nx, ny = locations[i]
                visited[i] = 1
                dfs(selected + [(nx, ny)], distance + abs(nx - selected[-1][0]) + abs(ny - selected[-1][1]))
                visited[i] = 0

    answer = 1e9
    visited = [0] * n
    dfs([company], 0)
    print(f"#{test_case} {answer}")