# MST
n = int(input())

stars = []
for _ in range(n):
    x, y = map(float, input().split())
    stars.append((x, y))

def find(x):
    if parents[x] != x:
        parents[x] = find(parents[x])
    return parents[x]

def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parents[b] = a
    else:
        parents[a] = b

parents = [i for i in range(n)]

def dist(star_a, star_b):
    ax, ay = star_a
    bx, by = star_b

    width = max(ax, bx) - min(ax, bx)
    height = max(ay, by) - min(ay, by)

    distance_powered = (width ** 2) + (height ** 2)
    return distance_powered ** 0.5

graph = []
for i in range(n):
    for j in range(i + 1, n):
        graph.append((i, j, dist(stars[i], stars[j])))

graph.sort(key=lambda x:x[2])

answer = 0
for x, y, d in graph:
    if find(x) != find(y):
        union(x, y)
        answer += d

print(answer)