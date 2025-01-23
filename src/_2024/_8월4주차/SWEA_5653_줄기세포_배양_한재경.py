#2097ms, bfs
from collections import defaultdict, deque
class StemCell:
    def __init__(self, vitality, state, time):
        self.vitality = vitality  # 생명력
        self.state = state  # 0: 비활성, 1: 활성, 2: 사망
        self.time = time  # 현재 상태로 있는 시간


def simulate(N, M, K, initial_grid):

    # 방향 벡터: 상, 하, 좌, 우
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    # 그리드를 중심으로 확장할 것이므로 큼직하게 잡음
    offset = K + 50 #여기서부터 값 넣음
    grid = defaultdict(lambda: None) #(1, 2): StemCell(3, 0, 0) 형태로 키-값 연결

    # 초기 상태 설정
    for r in range(N):
        for c in range(M):
            if initial_grid[r][c] > 0:
                grid[(r + offset, c + offset)] = StemCell(initial_grid[r][c], 0, 0)

    for t in range(1, K + 1): #k초만큼
        new_grid = defaultdict(lambda: None) #이번 턴 그리드!

        # 각 세포 업데이트
        # list()로 새객체 만들어 도중에 grid수정돼도 영향x
        for (x, y), cell in list(grid.items()): #items(): 키-값쌍 튜플로 반환

            if cell.state == 0:  # 비활성 상태
                cell.time += 1
                if cell.time == cell.vitality:  # 활성화 시점
                    cell.state = 1 #활성 상태로 변환
                    cell.time = 0 #활성 시간 초기화

            elif cell.state == 1:  # 활성 상태
                if cell.time == 0:  # 활성화된 첫 시간에만 번식
                    for d in directions:
                        nx, ny = x + d[0], y + d[1]
                        if grid[(nx, ny)] is None:  # 해당 위치가 이전에 비어있는 경우에만 번식
                            if new_grid[(nx, ny)] is None: # 이번 턴에 셀 겹치지 않은 경우
                                new_grid[(nx, ny)] = StemCell(cell.vitality, 0, 0)
                            else:
                                # 동시에 번식하려는 경우, 생명력 큰 세포가 차지
                                if new_grid[(nx, ny)].vitality < cell.vitality:
                                    new_grid[(nx, ny)] = StemCell(cell.vitality, 0, 0)
                cell.time += 1
                if cell.time == cell.vitality:  # 사망 시점
                    cell.state = 2

        # 기존 그리드에 이번턴 그리드 번식 내용 추가
        for key, new_cell in new_grid.items():
            if grid[key] is None:
                grid[key] = new_cell

    # 살아있는 세포 수 계산 (비활성 + 활성 상태)
    alive_cells = sum(1 for cell in grid.values() if cell.state != 2)
    return alive_cells


T = int(input().strip())
results = []
for test_case in range(1, T + 1):
    N, M, K = map(int, input().strip().split())
    initial_grid = [list(map(int, input().strip().split())) for _ in range(N)]
    result = simulate(N, M, K, initial_grid)
    results.append(f"#{test_case} {result}")

for result in results:
    print(result)