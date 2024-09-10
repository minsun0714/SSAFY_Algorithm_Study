# 백트래킹

n = int(input())

nums = list(map(int, input().split()))

ops = list(map(int, input().split()))

def calculate(prev, op, cur):
    if op == 0:
        return prev + cur
    elif op == 1:
        return prev - cur
    elif op == 2:
        return prev * cur
    else:
        if prev < 0 < cur:
            return -(-prev // cur)
        return prev // cur

def back_tracking(selected, depth):
    global max_val, min_val
    if depth == n - 1:
        max_val = max(max_val, selected)
        min_val = min(min_val, selected)
        return
    for i in range(4):
        if ops[i] == 0:
            continue
        ops[i] -= 1
        back_tracking(calculate(selected, i, nums[depth + 1]), depth + 1)
        ops[i] += 1

max_val = -1e10
min_val = 1e10
back_tracking(nums[0], 0)
print(max_val)
print(min_val)