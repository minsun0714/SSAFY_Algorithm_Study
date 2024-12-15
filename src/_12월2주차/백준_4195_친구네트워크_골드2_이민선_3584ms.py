# union find
n = int(input())

def find(x):
    if parents[x] != x:
        parents[x] = find(parents[x])
    return parents[x]

def union(a, b):
    root_a = find(a)
    root_b = find(b)

    if root_a != root_b:
        parents[root_b] = root_a
        visited[root_a] += visited[root_b]

for _ in range(n):
    f = int(input())

    parents = {}
    visited = {}
    for _ in range(f):
        a, b = list(map(str, input().split()))
        if a not in parents:
            parents[a] = a
            visited[a] = 1
        if b not in parents:
            parents[b] = b
            visited[b] = 1

        union(a, b)


        print(visited[find(b)])



