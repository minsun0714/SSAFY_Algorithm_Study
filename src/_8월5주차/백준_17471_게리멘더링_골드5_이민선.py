from collections import defaultdict

n = int(input())

populations = [x for x in map(int, input().split())]

graph = defaultdict(list)
for i in range(1, n + 1):
    adj_info = list(map(int, input().split()))
    for adj in adj_info[1:]:
        graph[i].append(adj)


def check(team):
    start = team[0]
    stack = [start]
    visited = [0] * (n + 1)
    while stack:
        cur = stack.pop()
        visited[cur] = 1
        for next in graph[cur]:
            if not visited[next] and next in team:
                stack.append(next)

    return visited.count(1) == len(team)


def back_tracking(team_a, depth, start):
    if n > depth > 0:
        team_b = [x for x in range(1, n + 1) if x not in team_a]
        if check(team_a) and check(team_b):
            sum_a = sum([populations[p - 1] for p in team_a])
            sum_b = sum([populations[p - 1] for p in team_b])
            global answer
            answer = min(answer, abs(sum_a - sum_b))

    if depth == n:
        return
    for i in range(start, n + 1):
        back_tracking(team_a + [i], depth + 1, i + 1)


answer = 1e9
back_tracking([], 0, 1)
print(-1 if answer == 1e9 else answer)