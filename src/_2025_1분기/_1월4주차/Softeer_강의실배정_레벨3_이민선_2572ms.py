import sys

n = int(sys.stdin.readline())

courses = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

courses.sort(key=lambda x:x[1])

count = 0
prev_e = 0
for s, e in courses:
    if s >= prev_e:
        prev_e = e
        count += 1
print(count)