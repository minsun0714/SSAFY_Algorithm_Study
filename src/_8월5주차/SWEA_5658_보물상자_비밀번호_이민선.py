from collections import deque

T = int(input())

for t in range(1, T + 1):
    n, k = map(int, input().split())

    num = list(input())
    deq = deque(num)

    s = set()
    pw_length = len(num) // 4

    for i in range(pw_length):
        for j in range(0, len(num), pw_length):
            word = "".join((list(deq)[j:j + pw_length]))
            s.add(word)
        last = deq.pop()
        deq.appendleft(last)
    l = list(s)
    l.sort(reverse=True)
    answer = l[k - 1]
    print(f"#{t} {int(answer, 16)}")



