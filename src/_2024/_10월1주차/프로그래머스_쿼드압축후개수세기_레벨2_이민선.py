def divide(arr, answer):
    arr = conquer(arr)

    if len(arr) == 1:
        answer[arr[0][0]] += 1
        return answer

    mid = len(arr) // 2

    box1 = [row[:mid] for row in arr[:mid]]
    box2 = [row[mid:] for row in arr[:mid]]
    box3 = [row[:mid] for row in arr[mid:]]
    box4 = [row[mid:] for row in arr[mid:]]

    for box in [box1, box2, box3, box4]:
        divide(box, answer)

    return answer


def conquer(arr):
    count = 0
    for row in arr:
        for val in row:
            if val == 0:
                count += 1

    if count == len(arr[0]) ** 2 or count == 0:
        return [[arr[0][0]]]
    else:
        return arr


def solution(arr):
    return divide(arr, [0, 0])