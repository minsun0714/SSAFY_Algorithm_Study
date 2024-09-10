T = int(input())

result = ""
for t in range(1, T + 1):
    n = int(input())

    atoms = []

    for _ in range(n):
        x, y, d, k = map(int, input().split())
        x *= 2
        y *= 2
        atoms.append((x, y, d, k))

    answer = 0

    while atoms:
        dx = [0, 0, -1, 1]
        dy = [1, -1, 0, 0]

        cur_atoms = set()
        first_arrived = set()
        explosion = set()

        for i in range(len(atoms)):
            x, y, d, k = atoms[i]

            nx, ny = x + dx[d], y + dy[d]
            
            # 범위 내에 있으면 이동
            # 범위 밖으로 벗어나면 충돌할 일이 없으므로 소멸
            if 2000 >= nx >= -2000 and 2000 >= ny >= -2000:
                cur_atoms.add((nx, ny, d, k))

            if (nx, ny) not in first_arrived:
                first_arrived.add((nx, ny))
            else:
                explosion.add((nx, ny))

        atoms = []
        for atom in cur_atoms:
            x, y, d, k = atom
            if (x, y) in explosion:
                answer += k
            else:
                atoms.append(atom)

    result += f"#{t} {answer}\n"
print(result)








