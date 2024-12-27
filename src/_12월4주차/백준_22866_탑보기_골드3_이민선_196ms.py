# 스택

import sys

n = int(input())
heights = list(enumerate(map(int, sys.stdin.readline().split()), start=1))


answer1 = [0] * (n + 1)
answer2 = [1e9] * (n + 1)

def solution(buildings):
    stack = []
    for i, cur in buildings:
        while stack and stack[-1][1] <= cur:
            stack.pop()
            # 왼쪽, 오른쪽 각각에 있는 것들 중 보이는 것들 = stack에 들어있는 것들
        answer1[i] += len(stack)
        if stack  and abs(stack[-1][0] - i) < abs(answer2[i] - i):
            answer2[i] = stack[-1][0]
        stack.append((i, cur))

solution(heights)
print(answer1) #빈 0번 인덱스가 생긴다.
solution(heights[::-1]) # 1부터 시작하면
print(answer1) #빈 0번 인덱스가 생긴다.
for count, nearest in list(zip(answer1, answer2))[1:]:
    if count == 0:
        print(0)
    else:
        print(count, nearest)
