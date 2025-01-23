# 이분 탐색

n, c = map(int, input().split())

houses = []
for _ in range(n):
    houses.append(int(input()))
houses.sort()

s, e = 1, houses[-1]
result = 0
while s <= e:
    mid = (s + e) // 2

    count = 1
    prev = 0
    for i in range(1, n):
        if houses[i] - houses[prev] < mid:
            continue
        else:
            count += 1
            prev = i
    if count >= c:
        result = mid
        s = mid + 1
    else:
        e = mid - 1
print(result)