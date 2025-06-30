# 이분탐색
import sys

n, m = map(int, sys.stdin.readline().split())

videos = list(map(int, sys.stdin.readline().split()))
answer = 0

# 가능한 blueray의 크기 중 가장 작은 값은 videos의 최댓값
# 가능한 blueray의 크기 중 가장 큰 값은 videos의 합산값
s, e = max(videos), sum(videos)
while s <= e:
    mid = (s + e) // 2
    blueray = 0
    count = 1
    for video in videos:
        if blueray + video <= mid:
            blueray += video
        else:
            blueray = video
            count += 1
    # blueray의 개수가 m과 같은 경우 더 작은 값이 있는지 계속 탐색
    # blueray의 개수가 m보다 작은 경우 blueray 크기의 가능한 최대값을 줄여서 계속 탐색
    if count <= m:
        e = mid - 1
        answer = mid
    else:
        s = mid + 1
print(answer)