n = int(input())
m = int(input())

info = [list(map(int, input().split())) for _ in range(n)]
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
for i in range(n):
    for j in range(i):
        if info[i][j]:
            union(i, j)

for i in range(n):
    find(i)

plans = list(map(int, input().split()))
plan_parents = [parents[plan - 1] for plan in plans]
print('YES' if len(set(plan_parents)) == 1 else 'NO')