n = int(input())

required_budgets = list(map(int, input().split()))
required_budgets.sort()

m = int(input())

s, e = 1 , required_budgets[n - 1]
result = 0
while s <= e:
    mid = (s + e) // 2
    total = 0
    for budget in required_budgets:
        if budget < mid:
            total += budget
        else:
            total += mid
    if total > m:
        e = mid - 1
    else:
        result = mid
        s = mid + 1
print(result)