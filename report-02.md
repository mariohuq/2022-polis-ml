---
author: Кондраев Дмитрий
title: ДЗ 2
---

# Приведите пример Map only и Reduce задачи. (1 балл)

Пример Map-only задачи --- поменять порядок колонок в дата-сете, добавить новую колонку по заранее известному правилу, зависящему только от текущей строки.

Reduce-only --- подсчет общего количества входных данных.

# Может ли стадия Reduce начаться до завершения стадии Map? Почему? (2 балл)

Да, копирование результатов уже закончивших работу Mappers и сортировка может начинаться до полного завершения Map-задач. Но выполнение самого Reduce может начаться только когда все Mappers закончат работу, так как записи сортируются по ключу и если начать Reduce раньше, не будет гарантии, что все записи с определенным ключом будут обработаны должным образом.

# Разверните кластер hadoop, соберите WordCount приложение, запустите на датасете ppkm_sentiment и выведите 10 самых редких слов (1 балл)

Старт контейнера

```shell
$ systemctl start docker.service
$ docker start hdp -i
```

Сборка и копирование Jar с WordCount Job (версия на Java):

```shell
$ cd 02-mapreduce/wordcount
$ ./gradlew build
$ docker cp ./**/wordcount-1.0-SNAPSHOT.jar hdp:/home/hduser/ 
$ cd ..
```

Запуск первой задачи (подсчет слов):

```shell
hduser@localhost:~$ hdfs dfs -rm -r ppkm.out-java
hduser@localhost:~$ hadoop jar wordcount-1.0-SNAPSHOT.jar ok.ml.WordCount\
  -Dwordcount.input=ppkm/ppkm_dataset.csv -Dwordcount.output=ppkm.out-java
```

Сборка и копирование Jar с wordswap Job (версия на Scala):

```shell
$ cd scala-wordswap
$ sbt assembly
$ docker cp ./**/wordswap-assembly-0.1.0-SNAPSHOT.jar hdp:/home/hduser/
```

Запуск второй задачи (меняет местами слово и количество повторений):

```shell
hduser@localhost:~$ hdfs dfs -rm -r ppkm.out-scala
hduser@localhost:~$ hadoop jar wordswap-assembly-0.1.0-SNAPSHOT.jar\
  -Dswap.input=ppkm.out-java -Dswap.output=ppkm.out-scala
```

10 самых редких слов:

```shell
hduser@localhost:~$ hdfs dfs -cat ppkm.out-scala/part-r-00000 | head -11
1 ah
1 bs
1 |
1 y
1 wi
1 w
1 tu
1 (
1 to
1 sm
1 rs
```

# Перепишите WordCount на Scala (2 балла)



# Измените маппер в WordCount так, чтобы он удалял знаки препинания и приводил все слова к единому регистру (Java: 1 балл, Scala: 2 балла)




# На кластере лежит датасет, в котором ключами является id сотрудника и дата, а значением размер выплаты. Руководитель поставил задачу рассчитать среднюю сумму выплат по каждому сотруднику за последний месяц. В маппере вы отфильтровали старые записи и отдали ключ-значение вида: id-money. А в редьюсере суммировали все входящие числа и поделили результат на их количество. Но вам в голову пришла идея оптимизировать расчет, поставив этот же редьюсер и в качестве комбинатора, тем самым уменьшив шафл данных. Можете ли вы так сделать? Если да, то где можно было допустить ошибку? Если нет, то что должно быть на выходе комбинатора? (2 балла)