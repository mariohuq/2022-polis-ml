#!/usr/bin/env python

import sys
from itertools import groupby
from operator import itemgetter

def parse(line):
    line = line.strip()
    word, count = line.split('\t', 1)
    # приводим тип count к int
    try:
        return word, int(count)
    except ValueError:
        return None


wordcounts = (parse(line) for line in sys.stdin)
wordcounts = (p for p in wordcounts if p)
# hadoop sorts given lines lexicographically => groupby works
for word, group in groupby(wordcounts, key=itemgetter(0)):
    print(word, sum(count for _, count in group), sep='\t')