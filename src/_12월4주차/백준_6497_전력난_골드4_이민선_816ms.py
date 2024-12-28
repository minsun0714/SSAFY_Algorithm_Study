def find(x):
    if x != parent[x]:
        parent[x] = find(parent[x])
    return parent[x]


def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = a

    else:
        parent[a] = b

while True:
    m, n = map(int, input().split())

    if m == n == 0:
        break

    graph = []
    cur_cost = 0

    for _ in range(n):
        x, y, z = list(map(int, input().split()))

        graph.append((x, y, z))

        cur_cost += z

    graph.sort(key=lambda x: x[2])

    parent = [i for i in range(m)]

    minimum_cost = 0
    for x, y, z in graph:
        if find(x) != find(y):
            union(x, y)
            minimum_cost += z

    print(cur_cost - minimum_cost)


