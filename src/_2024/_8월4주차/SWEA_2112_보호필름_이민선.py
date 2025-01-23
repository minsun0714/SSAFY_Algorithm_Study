
# 3294ms
# 백트래킹
T = int(input())

def check():
    for j in range(w):
        dp = [1] * d
        for i in range(1, d):
            if board[i][j] == board[i - 1][j]:
                dp[i] = dp[i - 1] + 1
        if max(dp) < k:
            return False
    return True

def back_tracking(depth, start):
    global answer
    if check():
        answer = min(answer, depth)

    if depth == d - 1:
        return

    for i in range(start, d):
        if depth >= answer:
            continue
        temp = board[i]
        board[i] = [0] * w
        back_tracking(depth + 1, i + 1)
        board[i] = [1] * w
        back_tracking(depth + 1, i + 1)
        board[i] = temp

for t in range(1, T + 1):
    d, w, k = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(d)]

    if k == 1:
        print(f"#{t} 0")
    else:
        answer = d
        back_tracking(0, 0)
        print(f"#{t} {answer}")
