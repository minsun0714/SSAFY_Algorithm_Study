import sys
n, m = map(int, sys.stdin.readline().split())

spend_list = []
for _ in range(n):
    spend_list.append(int(sys.stdin.readline()))

s, e = max(spend_list), sum(spend_list)
answer = 0
while s <= e:
    mid = (s + e) // 2

    total = 0
    count = 1
    for spend in spend_list:
        if total + spend <= mid:
            total += spend
        else:
            total = spend
            count += 1

    if count > m:
        s = mid + 1
    else:
        e = mid - 1
        answer = mid

print(answer)