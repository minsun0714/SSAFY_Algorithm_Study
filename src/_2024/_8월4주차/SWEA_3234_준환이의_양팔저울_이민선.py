# 2847ms
# 백트래킹

T = int(input())

for t in range(1, T + 1):
    n = int(input())

    nums = list(map(int, input().split()))
    answer = 0

    def factorial(n):
        f = 1
        while n > 1:
            f *= n
            n -= 1
        return f

    def back_tracking(left, right, depth):
        global answer

        if left < right:
            return

        if left > total - left:
            answer += factorial(n - depth) * 2 ** (n - depth)
            return

        if depth == n:
            answer += 1
            return



        for i in range(len(nums)):
            if visited[i]:
                continue
            visited[i] = 1
            back_tracking(left + nums[i], right, depth + 1)
            back_tracking(left, right + nums[i], depth + 1)
            visited[i] = 0


    total = sum(nums)
    visited = [0] * n
    back_tracking(0, 0, 0)

    print(f"#{t} {answer}")