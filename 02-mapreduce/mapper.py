#!/usr/bin/env python
import sys

for line in sys.stdin:
    line = line.strip()
    words = line.split()
    for word in words:
        # данные представляют собой пару ключ-значение, разделенные табуляцией,
        # где ключ - слово, а значение всегда 1
        print(word, 1, sep='\t')