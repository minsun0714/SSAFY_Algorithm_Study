import sys
sys.setrecursionlimit(10**8)

n, m = map(int, input().split())

parent = [i for i in range(n + 1)]

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]

def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b

for _ in range(m):
    cal, a, b = map(int, input().split())

    if cal == 0:
        union(a, b)
    else:
        print('YES' if find(a) == find(b) else 'NO')

