t = 0

while True:
    t += 1
    n, m = map(int, input().split())
    if n == 0 and m == 0:
        break

    def find_parents(x):
        if parents[x] != x:
            parents[x] = find_parents(parents[x])  # 경로 압축
        return parents[x]

    def union_parents(a, b):
        root_a = find_parents(a)
        root_b = find_parents(b)

        if root_a == root_b:
            return True  # 사이클 발생
        if root_a < root_b:
            parents[root_b] = root_a
        else:
            parents[root_a] = root_b
        return False

    parents = [i for i in range(n + 1)]
    cycle_nodes = set()

    # 간선 정보 처리
    for _ in range(m):
        a, b = map(int, input().split())
        if union_parents(a, b):  # 사이클 발생 시
            cycle_nodes.add(a)
            cycle_nodes.add(b)

    # 모든 노드의 루트를 최신화
    for i in range(1, n + 1):
        find_parents(i)

    # 각 루트별로 노드 그룹 확인
    tree_roots = {}
    for i in range(1, n + 1):
        root = find_parents(i)
        if root not in tree_roots:
            tree_roots[root] = []
        tree_roots[root].append(i)

    # 유효한 트리의 개수 계산
    valid_trees = 0
    for root, nodes in tree_roots.items():
        if any(node in cycle_nodes for node in nodes):  # 사이클이 포함된 트리 제외
            continue
        valid_trees += 1

    # 결과 출력
    if valid_trees == 0:
        print(f"Case {t}: No trees.")
    elif valid_trees == 1:
        print(f"Case {t}: There is one tree.")
    else:
        print(f"Case {t}: A forest of {valid_trees} trees.")
