def solution(game_board, table):
    def dfs(board, x, y, pieces, target):
        if x < 0 or x >= len(board) or y < 0 or y >= len(board[0]):
            return False
        if board[x][y] != target:
            return False
        board[x][y] = 1 - target
        pieces.append((x, y))
        pieces.sort()
        dfs(board, x - 1, y, pieces, target)
        dfs(board, x + 1, y, pieces, target)
        dfs(board, x, y - 1, pieces, target)
        dfs(board, x, y + 1, pieces, target)

        return pieces

    puzzles = []
    for i in range(len(table)):
        for j in range(len(table)):
            if table[i][j] == 1:
                puzzle = dfs(table, i, j, [], 1)
                puzzles.append(puzzle)

    blanks = []
    for i in range(len(game_board)):
        for j in range(len(game_board)):
            if game_board[i][j] == 0:
                blank = dfs(game_board, i, j, [], 0)
                blanks.append(blank)

    def rotate(puzzle):
        # puzzle의 좌표가 주어졌을 때 90도로 회전
        min_x, min_y = min([x for x, y in puzzle]), min([y for x, y in puzzle])
        max_x, max_y = max([x for x, y in puzzle]), max([y for x, y in puzzle])

        temp = [[0] * (max_y - min_y + 1) for _ in range(max_x - min_x + 1)]

        for x, y in puzzle:
            temp[x - min_x][y - min_y] = 1
        temp = list(map(list, zip(*temp[::-1])))
        for i in range(max_x - min_x + 1):
            for j in range(max_y - min_y + 1):
                if temp[i][j] == 1:
                    return dfs(temp, i, j, [], 1)

    def can_put(blank, puzzle):
        # blank와 puzzle의 모든 x, y 좌표의 차이가 같으면 놓을 수 있는 것
        diff_x, diff_y = blank[0][0] - puzzle[0][0], blank[0][1] - puzzle[0][1]
        for (bx, by), (px, py) in zip(blank, puzzle):
            if bx - px != diff_x or by - py != diff_y:
                return False
        return True

    board_set = set()
    table_set = set()
    answer = 0
    for i in range(len(blanks)):
        for j in range(len(puzzles)):
            blank, puzzle = blanks[i], puzzles[j]
            if len(blank) != len(puzzle):
                continue
            for _ in range(4):
                puzzle = rotate(puzzle)
                if can_put(blank, puzzle) and not i in board_set and not j in table_set:
                    board_set.add(i)
                    table_set.add(j)
                    answer += len(blank)