def has_bingo(board):
    dp = [[[0] * 2 for _ in range(3)] for _ in range(3)]

    if board[0] == board[4] == board[8] != '.':
        return True
    if board[2] == board[4] == board[6] != '.':
        return True

    for i in range(9):
        r = i // 3
        c = i % 3
        if board[i] == '.':
            continue
        if i > 2 and r > 0 and board[i] == board[i - 3]:
            dp[r][c][0] = dp[r - 1][c][0] + 1
        if i > 0 and c > 0 and board[i] == board[i - 1]:
            dp[r][c][1] = dp[r][c - 1][1] + 1

        if 2 in dp[r][c]:
            return True
    return False


def simulation(depth, selected, visited):
    global answer
    if has_bingo(visited[0] + visited[1] + visited[2]):
        if len(selected) == len(O_list) + len(X_list):
            answer = 'valid'
        return

    if depth == 9:
        if not has_bingo(visited[0] + visited[1] + visited[2]):
            answer = 'valid'
        return

    for i in range(9):
        if answer == 'valid':
            continue
        if board[i] == '.':
            continue
        if depth % 2 == 0 and board[i] == 'O' or depth % 2 == 1 and board[i] == 'X':
            continue
        if visited[i // 3][i % 3] == '.':
            visited[i // 3][i % 3] = board[i]
            simulation(depth + 1, selected + [board[i]], visited)
            visited[i // 3][i % 3] = '.'

while True:
    input_ = input()
    if input_ == 'end':
        break
    board = list(map(str, input_))

    O_list = []
    X_list = []

    for i in range(9):
        row_idx = i // 3
        col_idx = i % 3

        if board[i] == 'O':
            O_list.append((row_idx, col_idx))
        elif board[i] == 'X':
            X_list.append((row_idx, col_idx))

    if abs(len(O_list) - len(X_list)) > 1:
        print('invalid')
        continue

    if len(O_list) > len(X_list):
        print('invalid')
        continue

    answer = 'invalid'
    simulation(0, [],[['.'] * 3 for _ in range(3)])
    print(answer)

