# 해시

from collections import defaultdict


def solution(genres, plays):
    dict = defaultdict(list)
    sum_dict = defaultdict(int)

    for i in range(len(genres)):
        dict[genres[i]].append((plays[i], i))
        sum_dict[genres[i]] += plays[i]

    for d in dict:
        dict[d].sort(key=lambda x: (-x[0], x[1]))

    rank = sorted(sum_dict.keys(), key=lambda x: -sum_dict[x])

    answer = []
    for r in rank:
        temp = []
        for i in range(2):
            if i < len(dict[r]):
                temp.append(dict[r][i][1])
        answer.extend(temp)

    return answer

