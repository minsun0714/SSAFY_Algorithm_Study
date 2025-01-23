n, k = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(n)]

move_board = [[[] for _ in range(n)] for _ in range(n)]
move_info = []

for i in range(k):
    r, c, d = map(int, input().split())

    move_board[r - 1][c - 1].append(i + 1)

    move_info.append([r - 1, c - 1, d])

turn = 1
dr, dc = [0, 0, 0, -1, 1], [0, 1, -1, 0, 0]

while turn:
    for i, (r, c, d) in enumerate(move_info):
        if move_board[r][c][0] != i + 1:  # 맨 아래 있는 말이 현재말이 아니면 다음 말로 continue
            continue

        nr, nc = r + dr[d], c + dc[d] #이동할 셀

        if 0 <= nr < n and 0 <= nc < n and board[nr][nc] != 2:
            if board[nr][nc] == 0:
                temp = move_board[r][c][:]
                for j in temp:
                    move_info[j - 1][0], move_info[j - 1][1] = nr, nc  # 실제 이동
                move_board[nr][nc].extend(move_board[r][c])
                move_board[r][c] = []
            else:
                temp = move_board[r][c][:]
                for j in temp:
                    move_info[j - 1][0], move_info[j - 1][1] = nr, nc# 실제 이동
                    move_board[nr][nc].append(move_board[r][c].pop())


        else:
            nd = d - 1 if d % 2 == 0 else d + 1
            move_info[i][2] = nd
            nnr, nnc = r + dr[nd], c + dc[nd]
            if 0 <= nnr < n and 0 <= nnc < n and board[nnr][nnc] != 2:
                temp = move_board[r][c][:]
                for j in temp:
                    move_info[j - 1][0], move_info[j - 1][1] = nnr, nnc  # 실제 이동
                    if board[nnr][nnc] == 0:
                        move_board[nnr][nnc].append(move_board[r][c].pop(0))
                    else:
                        move_board[nnr][nnc].append(move_board[r][c].pop())
                nr, nc = nnr, nnc
            else:
                nr, nc = r, c

        if len(move_board[nr][nc]) >= 4:
            print(turn)
            exit(0)
        if turn > 1000:
            print(-1)
            exit(0)
    turn += 1