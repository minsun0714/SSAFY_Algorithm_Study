# 이분탐색
k, n = map(int, input().split())

lans = []
for _ in range(k):
    lan = int(input())
    lans.append(lan)

lans.sort()

s, e = 1, 2 ** 31 - 1
answer = 0
while s <= e:
    mid = (s + e) // 2
    count = 0
    for lan in lans:
        count += lan // mid
    if count < n:
        e = mid - 1
    else:
        answer = mid # e가 아니라 mid가 답이 되는 이유
        s = mid + 1

print(answer)