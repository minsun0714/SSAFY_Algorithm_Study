# union find

import sys

sys.setrecursionlimit(10**8)

T = int(input())

def find(x):
    if x != parents[x]:
        parents[x] = find(parents[x])
    return parents[x]

def union(a, b):
    root_a = find(a)
    root_b = find(b)

    if root_a == root_b:
        return True
    elif root_a < root_b:
        parents[root_b] = root_a
    else:
        parents[root_a] = root_b
    return False

for _ in range(T):
    n = int(input())
    m = int(input())

    parents = [i for i in range(n + 1)]
    cycle = set()
    for _ in range(m):
        a, b = map(int, input().split())
        if union(a, b):
            cycle.add(a)
            cycle.add(b)

    for i in range(1, n + 1):
        find(i)

    if len(cycle):
        print('graph')
    else:
        s = set(parents[1:])
        if len(s) > 1:
            print('graph')
        else:
            print('tree')


