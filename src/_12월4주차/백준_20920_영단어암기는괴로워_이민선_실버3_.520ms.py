from collections import Counter

n, m = map(int, input().split())

words = [input() for _ in range(n)]
counter = Counter(words)

def get_freq(word):
    return counter[word]

words.sort(key=lambda x:(-get_freq(x), -len(x), x))

s = set()
for word in words:
    if len(word) >= m and word not in s:
        print(word)
        s.add(word)
