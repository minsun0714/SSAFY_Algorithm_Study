def solution(stones, k):
    s, e = 1, max(stones) * k
    answer = 0
    while s <= e:
        mid = (s + e) // 2
        count = 0
        for stone in stones:
            if stone > mid:
                count = 0
            else:
                count += 1
                if count >= k:
                    break
        if count >= k:
            e = mid - 1
            answer = mid
        else:
            s = mid + 1
    return answer