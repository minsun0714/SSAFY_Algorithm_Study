T = int(input())

for t in range(1, T + 1):
    m, a = map(int, input().split())

    a_info = list(map(int, input().split()))
    b_info = list(map(int, input().split()))

    board = [[[0] for _ in range(11)] for _ in range(11)]
    power_dict = {}
    power_dict[0] = 0
    for bc in range(1, a + 1):
        y, x, c, p = map(int, input().split())
    
        power_dict[bc] = p
        for i in range(x - c, x + c + 1):
            for j in range(y - c, y + c + 1):
                if i < 0 or j < 0 or i > 10 or j > 10:
                    continue
                if abs(x - i) + abs(y - j) <= c:
                    board[i][j].append(bc)
    
    dx = [0, -1, 0, 1, 0]
    dy = [0, 0, 1, 0, -1]

    ax, ay = 1, 1
    bx, by = 10, 10

    answer = 0
    for d in range(m + 1):
        max_val = 0
        for bc_a in board[ax][ay]:
            for bc_b in board[bx][by]:
                if bc_a == bc_b:
                    max_val = max(max_val, power_dict[bc_a])
                else:
                    max_val = max(max_val, power_dict[bc_a] + power_dict[bc_b])
        answer += max_val
        if d == m: break
        ax, ay = ax + dx[a_info[d]], ay + dy[a_info[d]]
        bx, by = bx + dx[b_info[d]], by + dy[b_info[d]]
    
    print(f"#{t} {answer}")