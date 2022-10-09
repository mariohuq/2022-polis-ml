#!/usr/bin/env python

from operator import itemgetter
import sys

current_word = None
current_count = 0
word = None
# получаем строку со стандартного ввода STDIN
for line in sys.stdin:
    # удаляем пробелы в начале и конце строки
    line = line.strip()
    # парсим данные, полученные из mapper.py
    word, count = line.split('\t', 1)
    # приводим тип count к int
    try:
        count = int(count)
    except ValueError:
        # count не является числом, поэтому
        # игнорируем строку
        continue
    # условие написано с учетом того, что Hadoop всегда сортирует
    # данные по ключу перед передачей на вход редьюсера
    if current_word == word:
        current_count += count
    else:
        if current_word:
        # пишем результат в STDOUT
            print(current_word, current_count, sep='\t')
        current_count = count
        current_word = wordReducer.py
# не забываем вывести последнее слово, так как предыдущий вывод
# работал только при смене слова
if current_word == word:
    print(current_word, current_count, sep='\t')