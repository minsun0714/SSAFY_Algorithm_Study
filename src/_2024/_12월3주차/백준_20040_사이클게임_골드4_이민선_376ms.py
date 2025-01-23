# union find
n, m = map(int, input().split())

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

parent = [i for i in range(n)]
count = 1
answer = 0
for _ in range(m):
    a, b = map(int, input().split())
    if find(a) != find(b):
        union(a, b)
        count += 1
    else:
        answer = count
        break
print(answer)
