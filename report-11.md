---
author: Кондраев Дмитрий
title: ДЗ 11
---

Задание --- напишите юнит-тест `WordCount`-приложения на Spark Streaming (10 баллов).
<!-- или Spark Structure Streaming -->

Исходники см. в репозитории <https://github.com/mariohuq/2022-polis-ml>.
Команды выполняются из корневой папки репозитория:

```bash
$ git clone https://github.com/mariohuq/2022-polis-ml.git kondraev-ml
$ cd kondraev-ml
```


# Запуск `WordCount`, читающего из TCP-сокета

1.  Старт контейнера, созданного в прошлом ДЗ:

	```bash
	$ docker start -i zeppelin
	```
2.  Сборка и копирование `WordCount`[^1]

	```bash
	$ cd 11-spark-streaming-test/WordCountStreaming/
	$ sbt package
	$ docker cp ./**/wordcountstreaming_2.11-0.1.0-SNAPSHOT.jar zeppelin:/home/hduser/
	```
3.  Запуск источника данных (TCP-сокет)

	```bash
	$ docker exec -it zeppelin bash # запуск второго терминала для контейнера
	hduser@localhost:~$ sudo apt-get install netcat
	hduser@localhost:~$ nc -lkp 9999
	```
4.  Запуск обработчика

	```bash
	# в первом терминале
	hduser@localhost:~$ spark-submit --class ok.ml.WordCount \
	    wordcountstreaming_2.11-0.1.0-SNAPSHOT.jar
	```

# Запуск юнит теста

```bash
$ cd 11-spark-streaming-test/WordCountStreaming/
$ sbt test
```
<!-- 
Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
22/12/11 19:14:09 INFO WordCountTest: Using manual clock
22/12/11 19:14:09 INFO WordCountTest: 

===== TEST OUTPUT FOR ok.ml.WordCountTest: 'WordCount basic' =====

22/12/11 19:14:09 INFO SparkContext: Running Spark version 2.4.8
22/12/11 19:14:10 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
22/12/11 19:14:10 INFO SparkContext: Submitted application: WordCountTest
22/12/11 19:14:10 INFO SecurityManager: Changing view acls to: mhq
22/12/11 19:14:10 INFO SecurityManager: Changing modify acls to: mhq
22/12/11 19:14:10 INFO SecurityManager: Changing view acls groups to: 
22/12/11 19:14:10 INFO SecurityManager: Changing modify acls groups to: 
22/12/11 19:14:10 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users  with view permissions: Set(mhq); groups with view permissions: Set(); users  with modify permissions: Set(mhq); groups with modify permissions: Set()
22/12/11 19:14:10 INFO Utils: Successfully started service 'sparkDriver' on port 46149.
22/12/11 19:14:10 INFO SparkEnv: Registering MapOutputTracker
22/12/11 19:14:10 INFO SparkEnv: Registering BlockManagerMaster
22/12/11 19:14:10 INFO BlockManagerMasterEndpoint: Using org.apache.spark.storage.DefaultTopologyMapper for getting topology information
22/12/11 19:14:10 INFO BlockManagerMasterEndpoint: BlockManagerMasterEndpoint up
22/12/11 19:14:10 INFO DiskBlockManager: Created local directory at /tmp/blockmgr-f1b78e85-7139-462c-ab6f-bdd6bc0bc2c4
22/12/11 19:14:10 INFO MemoryStore: MemoryStore started with capacity 819.3 MB
22/12/11 19:14:10 INFO SparkEnv: Registering OutputCommitCoordinator
22/12/11 19:14:11 INFO Utils: Successfully started service 'SparkUI' on port 4040.
22/12/11 19:14:11 INFO SparkUI: Bound SparkUI to 0.0.0.0, and started at http://envy:4040
22/12/11 19:14:11 INFO Executor: Starting executor ID driver on host localhost
22/12/11 19:14:11 INFO Utils: Successfully started service 'org.apache.spark.network.netty.NettyBlockTransferService' on port 42281.
22/12/11 19:14:11 INFO NettyBlockTransferService: Server created on envy:42281
22/12/11 19:14:11 INFO BlockManager: Using org.apache.spark.storage.RandomBlockReplicationPolicy for block replication policy
22/12/11 19:14:11 INFO BlockManagerMaster: Registering BlockManager BlockManagerId(driver, envy, 42281, None)
22/12/11 19:14:11 INFO BlockManagerMasterEndpoint: Registering block manager envy:42281 with 819.3 MB RAM, BlockManagerId(driver, envy, 42281, None)
22/12/11 19:14:11 INFO BlockManagerMaster: Registered BlockManager BlockManagerId(driver, envy, 42281, None)
22/12/11 19:14:11 INFO BlockManager: Initialized BlockManager: BlockManagerId(driver, envy, 42281, None)
22/12/11 19:14:12 INFO WordCountTest: numBatches = 4, numExpectedOutput = 4
22/12/11 19:14:12 INFO TestInputStream: Slide time = 1000 ms
22/12/11 19:14:12 INFO TestInputStream: Storage level = Serialized 1x Replicated
22/12/11 19:14:12 INFO TestInputStream: Checkpoint interval = null
22/12/11 19:14:12 INFO TestInputStream: Remember interval = 1000 ms
22/12/11 19:14:12 INFO TestInputStream: Initialized and validated org.apache.spark.streaming.TestInputStream@3a9cbd2
22/12/11 19:14:12 INFO FlatMappedDStream: Slide time = 1000 ms
22/12/11 19:14:12 INFO FlatMappedDStream: Storage level = Serialized 1x Replicated
22/12/11 19:14:12 INFO FlatMappedDStream: Checkpoint interval = null
22/12/11 19:14:12 INFO FlatMappedDStream: Remember interval = 1000 ms
22/12/11 19:14:12 INFO FlatMappedDStream: Initialized and validated org.apache.spark.streaming.dstream.FlatMappedDStream@3d01eeb3
22/12/11 19:14:12 INFO MappedDStream: Slide time = 1000 ms
22/12/11 19:14:12 INFO MappedDStream: Storage level = Serialized 1x Replicated
22/12/11 19:14:12 INFO MappedDStream: Checkpoint interval = null
22/12/11 19:14:12 INFO MappedDStream: Remember interval = 1000 ms
22/12/11 19:14:12 INFO MappedDStream: Initialized and validated org.apache.spark.streaming.dstream.MappedDStream@1b09bed0
22/12/11 19:14:12 INFO ShuffledDStream: Slide time = 1000 ms
22/12/11 19:14:12 INFO ShuffledDStream: Storage level = Serialized 1x Replicated
22/12/11 19:14:12 INFO ShuffledDStream: Checkpoint interval = null
22/12/11 19:14:12 INFO ShuffledDStream: Remember interval = 1000 ms
22/12/11 19:14:12 INFO ShuffledDStream: Initialized and validated org.apache.spark.streaming.dstream.ShuffledDStream@6fc5b6f7
22/12/11 19:14:12 INFO TestOutputStreamWithPartitions: Slide time = 1000 ms
22/12/11 19:14:12 INFO TestOutputStreamWithPartitions: Storage level = Serialized 1x Replicated
22/12/11 19:14:12 INFO TestOutputStreamWithPartitions: Checkpoint interval = null
22/12/11 19:14:12 INFO TestOutputStreamWithPartitions: Remember interval = 1000 ms
22/12/11 19:14:12 INFO TestOutputStreamWithPartitions: Initialized and validated org.apache.spark.streaming.TestOutputStreamWithPartitions@4a2c80e2
22/12/11 19:14:12 INFO RecurringTimer: Started timer for JobGenerator at time 1000
22/12/11 19:14:12 INFO JobGenerator: Started JobGenerator at 1000 ms
22/12/11 19:14:12 INFO JobScheduler: Started JobScheduler
22/12/11 19:14:12 INFO StreamingContext: StreamingContext started
22/12/11 19:14:12 INFO WordCountTest: Manual clock before advancing = 0
22/12/11 19:14:12 INFO WordCountTest: Manual clock after advancing = 4000
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO TestInputStream: Computing RDD for time 1000 ms
22/12/11 19:14:12 INFO TestInputStream: Created RDD 0 with List(a, a)
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO JobScheduler: Added jobs for time 1000 ms
22/12/11 19:14:12 INFO TestInputStream: Computing RDD for time 2000 ms
22/12/11 19:14:12 INFO JobScheduler: Starting job streaming job 1000 ms.0 from job set of time 1000 ms
22/12/11 19:14:12 INFO TestInputStream: Created RDD 4 with List(a a, a)
22/12/11 19:14:12 INFO JobScheduler: Added jobs for time 2000 ms
22/12/11 19:14:12 INFO TestInputStream: Computing RDD for time 3000 ms
22/12/11 19:14:12 INFO TestInputStream: Created RDD 9 with List(a, b)
22/12/11 19:14:12 INFO JobScheduler: Added jobs for time 3000 ms
22/12/11 19:14:12 INFO TestInputStream: Computing RDD for time 4000 ms
22/12/11 19:14:12 INFO TestInputStream: Created RDD 13 with List(a, a   b, b)
22/12/11 19:14:12 INFO JobScheduler: Added jobs for time 4000 ms
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO JobGenerator: Checkpointing graph for time 1000 ms
22/12/11 19:14:12 INFO DStreamGraph: Updating checkpoint data for time 1000 ms
22/12/11 19:14:12 INFO DStreamGraph: Updated checkpoint data for time 1000 ms
22/12/11 19:14:12 INFO CheckpointWriter: Submitted checkpoint of time 1000 ms to writer queue
22/12/11 19:14:12 INFO JobGenerator: Checkpointing graph for time 2000 ms
22/12/11 19:14:12 INFO DStreamGraph: Updating checkpoint data for time 2000 ms
22/12/11 19:14:12 INFO DStreamGraph: Updated checkpoint data for time 2000 ms
22/12/11 19:14:12 INFO CheckpointWriter: Submitted checkpoint of time 2000 ms to writer queue
22/12/11 19:14:12 INFO JobGenerator: Checkpointing graph for time 3000 ms
22/12/11 19:14:12 INFO DStreamGraph: Updating checkpoint data for time 3000 ms
22/12/11 19:14:12 INFO DStreamGraph: Updated checkpoint data for time 3000 ms
22/12/11 19:14:12 INFO CheckpointWriter: Submitted checkpoint of time 3000 ms to writer queue
22/12/11 19:14:12 INFO JobGenerator: Checkpointing graph for time 4000 ms
22/12/11 19:14:12 INFO DStreamGraph: Updating checkpoint data for time 4000 ms
22/12/11 19:14:12 INFO DStreamGraph: Updated checkpoint data for time 4000 ms
22/12/11 19:14:12 INFO CheckpointWriter: Submitted checkpoint of time 4000 ms to writer queue
22/12/11 19:14:12 INFO CheckpointWriter: Saving checkpoint for time 1000 ms to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-1000'
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO SparkContext: Starting job: setupStreams at WordCountTest.scala:6
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO CheckpointWriter: Checkpoint for time 1000 ms saved to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-1000', took 3770 bytes and 94 ms
22/12/11 19:14:12 INFO CheckpointWriter: Saving checkpoint for time 2000 ms to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-2000'
22/12/11 19:14:12 INFO CheckpointWriter: Checkpoint for time 2000 ms saved to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-2000', took 3772 bytes and 15 ms
22/12/11 19:14:12 INFO CheckpointWriter: Saving checkpoint for time 3000 ms to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-3000'
22/12/11 19:14:12 INFO DAGScheduler: Registering RDD 2 (countByValue at WordCount.scala:11) as input to shuffle 0
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO CheckpointWriter: Checkpoint for time 3000 ms saved to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-3000', took 3772 bytes and 16 ms
22/12/11 19:14:12 INFO DAGScheduler: Got job 0 (setupStreams at WordCountTest.scala:6) with 2 output partitions
22/12/11 19:14:12 INFO CheckpointWriter: Saving checkpoint for time 4000 ms to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000'
22/12/11 19:14:12 INFO DAGScheduler: Final stage: ResultStage 1 (setupStreams at WordCountTest.scala:6)
22/12/11 19:14:12 INFO DAGScheduler: Parents of final stage: List(ShuffleMapStage 0)
22/12/11 19:14:12 INFO DAGScheduler: Missing parents: List(ShuffleMapStage 0)
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO DAGScheduler: Submitting ShuffleMapStage 0 (MapPartitionsRDD[2] at countByValue at WordCount.scala:11), which has no missing parents
22/12/11 19:14:12 INFO CheckpointWriter: Checkpoint for time 4000 ms saved to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000', took 3769 bytes and 67 ms
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO MemoryStore: Block broadcast_0 stored as values in memory (estimated size 3.2 KB, free 819.3 MB)
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:12 INFO MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 1970.0 B, free 819.3 MB)
22/12/11 19:14:12 INFO BlockManagerInfo: Added broadcast_0_piece0 in memory on envy:42281 (size: 1970.0 B, free: 819.3 MB)
22/12/11 19:14:12 INFO SparkContext: Created broadcast 0 from broadcast at DAGScheduler.scala:1184
22/12/11 19:14:12 INFO DAGScheduler: Submitting 2 missing tasks from ShuffleMapStage 0 (MapPartitionsRDD[2] at countByValue at WordCount.scala:11) (first 15 tasks are for partitions Vector(0, 1))
22/12/11 19:14:12 INFO TaskSchedulerImpl: Adding task set 0.0 with 2 tasks
22/12/11 19:14:12 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0, localhost, executor driver, partition 0, PROCESS_LOCAL, 7861 bytes)
22/12/11 19:14:13 INFO TaskSetManager: Starting task 1.0 in stage 0.0 (TID 1, localhost, executor driver, partition 1, PROCESS_LOCAL, 7861 bytes)
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO Executor: Running task 1.0 in stage 0.0 (TID 1)
22/12/11 19:14:13 INFO Executor: Running task 0.0 in stage 0.0 (TID 0)
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO Executor: Finished task 0.0 in stage 0.0 (TID 0). 1060 bytes result sent to driver
22/12/11 19:14:13 INFO Executor: Finished task 1.0 in stage 0.0 (TID 1). 1017 bytes result sent to driver
22/12/11 19:14:13 INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 404 ms on localhost (executor driver) (1/2)
22/12/11 19:14:13 INFO TaskSetManager: Finished task 1.0 in stage 0.0 (TID 1) in 364 ms on localhost (executor driver) (2/2)
22/12/11 19:14:13 INFO TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool 
22/12/11 19:14:13 INFO DAGScheduler: ShuffleMapStage 0 (countByValue at WordCount.scala:11) finished in 0.584 s
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO DAGScheduler: looking for newly runnable stages
22/12/11 19:14:13 INFO DAGScheduler: running: Set()
22/12/11 19:14:13 INFO DAGScheduler: waiting: Set(ResultStage 1)
22/12/11 19:14:13 INFO DAGScheduler: failed: Set()
22/12/11 19:14:13 INFO DAGScheduler: Submitting ResultStage 1 (MapPartitionsRDD[6] at setupStreams at WordCountTest.scala:6), which has no missing parents
22/12/11 19:14:13 INFO MemoryStore: Block broadcast_1 stored as values in memory (estimated size 3.6 KB, free 819.3 MB)
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO MemoryStore: Block broadcast_1_piece0 stored as bytes in memory (estimated size 2.1 KB, free 819.3 MB)
22/12/11 19:14:13 INFO BlockManagerInfo: Added broadcast_1_piece0 in memory on envy:42281 (size: 2.1 KB, free: 819.3 MB)
22/12/11 19:14:13 INFO SparkContext: Created broadcast 1 from broadcast at DAGScheduler.scala:1184
22/12/11 19:14:13 INFO DAGScheduler: Submitting 2 missing tasks from ResultStage 1 (MapPartitionsRDD[6] at setupStreams at WordCountTest.scala:6) (first 15 tasks are for partitions Vector(0, 1))
22/12/11 19:14:13 INFO TaskSchedulerImpl: Adding task set 1.0 with 2 tasks
22/12/11 19:14:13 INFO TaskSetManager: Starting task 0.0 in stage 1.0 (TID 2, localhost, executor driver, partition 0, PROCESS_LOCAL, 7662 bytes)
22/12/11 19:14:13 INFO TaskSetManager: Starting task 1.0 in stage 1.0 (TID 3, localhost, executor driver, partition 1, ANY, 7662 bytes)
22/12/11 19:14:13 INFO Executor: Running task 0.0 in stage 1.0 (TID 2)
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO Executor: Running task 1.0 in stage 1.0 (TID 3)
22/12/11 19:14:13 INFO ShuffleBlockFetcherIterator: Getting 0 non-empty blocks including 0 local blocks and 0 remote blocks
22/12/11 19:14:13 INFO ShuffleBlockFetcherIterator: Getting 2 non-empty blocks including 2 local blocks and 0 remote blocks
22/12/11 19:14:13 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 14 ms
22/12/11 19:14:13 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 13 ms
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO Executor: Finished task 0.0 in stage 1.0 (TID 2). 1171 bytes result sent to driver
22/12/11 19:14:13 INFO TaskSetManager: Finished task 0.0 in stage 1.0 (TID 2) in 93 ms on localhost (executor driver) (1/2)
22/12/11 19:14:13 INFO Executor: Finished task 1.0 in stage 1.0 (TID 3). 1318 bytes result sent to driver
22/12/11 19:14:13 INFO TaskSetManager: Finished task 1.0 in stage 1.0 (TID 3) in 97 ms on localhost (executor driver) (2/2)
22/12/11 19:14:13 INFO TaskSchedulerImpl: Removed TaskSet 1.0, whose tasks have all completed, from pool 
22/12/11 19:14:13 INFO DAGScheduler: ResultStage 1 (setupStreams at WordCountTest.scala:6) finished in 0.144 s
22/12/11 19:14:13 INFO DAGScheduler: Job 0 finished: setupStreams at WordCountTest.scala:6, took 0.996288 s
22/12/11 19:14:13 INFO WordCountTest: output.size = 0, numExpectedOutput = 4
22/12/11 19:14:13 INFO JobScheduler: Finished job streaming job 1000 ms.0 from job set of time 1000 ms
22/12/11 19:14:13 INFO JobScheduler: Total delay: 1670775252.623 s for time 1000 ms (execution: 1.105 s)
22/12/11 19:14:13 INFO JobScheduler: Starting job streaming job 2000 ms.0 from job set of time 2000 ms
22/12/11 19:14:13 INFO SparkContext: Starting job: setupStreams at WordCountTest.scala:6
22/12/11 19:14:13 INFO DAGScheduler: Registering RDD 7 (countByValue at WordCount.scala:11) as input to shuffle 1
22/12/11 19:14:13 INFO JobGenerator: Checkpointing graph for time 1000 ms
22/12/11 19:14:13 INFO DStreamGraph: Updating checkpoint data for time 1000 ms
22/12/11 19:14:13 INFO DAGScheduler: Got job 1 (setupStreams at WordCountTest.scala:6) with 2 output partitions
22/12/11 19:14:13 INFO DAGScheduler: Final stage: ResultStage 3 (setupStreams at WordCountTest.scala:6)
22/12/11 19:14:13 INFO DAGScheduler: Parents of final stage: List(ShuffleMapStage 2)
22/12/11 19:14:13 INFO DStreamGraph: Updated checkpoint data for time 1000 ms
22/12/11 19:14:13 INFO DAGScheduler: Missing parents: List(ShuffleMapStage 2)
22/12/11 19:14:13 INFO DAGScheduler: Submitting ShuffleMapStage 2 (MapPartitionsRDD[7] at countByValue at WordCount.scala:11), which has no missing parents
22/12/11 19:14:13 INFO MemoryStore: Block broadcast_2 stored as values in memory (estimated size 3.2 KB, free 819.3 MB)
22/12/11 19:14:13 INFO WordCountTest: output.size = 1, numExpectedOutput = 4
22/12/11 19:14:13 INFO CheckpointWriter: Saving checkpoint for time 1000 ms to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000'
22/12/11 19:14:13 INFO CheckpointWriter: Submitted checkpoint of time 1000 ms to writer queue
22/12/11 19:14:13 INFO MemoryStore: Block broadcast_2_piece0 stored as bytes in memory (estimated size 1970.0 B, free 819.3 MB)
22/12/11 19:14:13 INFO BlockManagerInfo: Added broadcast_2_piece0 in memory on envy:42281 (size: 1970.0 B, free: 819.3 MB)
22/12/11 19:14:13 INFO SparkContext: Created broadcast 2 from broadcast at DAGScheduler.scala:1184
22/12/11 19:14:13 INFO DAGScheduler: Submitting 2 missing tasks from ShuffleMapStage 2 (MapPartitionsRDD[7] at countByValue at WordCount.scala:11) (first 15 tasks are for partitions Vector(0, 1))
22/12/11 19:14:13 INFO TaskSchedulerImpl: Adding task set 2.0 with 2 tasks
22/12/11 19:14:13 INFO TaskSetManager: Starting task 0.0 in stage 2.0 (TID 4, localhost, executor driver, partition 0, PROCESS_LOCAL, 7863 bytes)
22/12/11 19:14:13 INFO TaskSetManager: Starting task 1.0 in stage 2.0 (TID 5, localhost, executor driver, partition 1, PROCESS_LOCAL, 7861 bytes)
22/12/11 19:14:13 INFO Executor: Running task 0.0 in stage 2.0 (TID 4)
22/12/11 19:14:13 INFO Executor: Running task 1.0 in stage 2.0 (TID 5)
22/12/11 19:14:13 INFO CheckpointWriter: Checkpoint for time 1000 ms saved to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000', took 3931 bytes and 43 ms
22/12/11 19:14:13 INFO WordCountTest: output.size = 1, numExpectedOutput = 4
22/12/11 19:14:13 INFO DStreamGraph: Clearing checkpoint data for time 1000 ms
22/12/11 19:14:13 INFO Executor: Finished task 1.0 in stage 2.0 (TID 5). 1017 bytes result sent to driver
22/12/11 19:14:13 INFO TaskSetManager: Finished task 1.0 in stage 2.0 (TID 5) in 26 ms on localhost (executor driver) (1/2)
22/12/11 19:14:13 INFO DStreamGraph: Cleared checkpoint data for time 1000 ms
22/12/11 19:14:13 INFO Executor: Finished task 0.0 in stage 2.0 (TID 4). 1017 bytes result sent to driver
22/12/11 19:14:13 INFO TaskSetManager: Finished task 0.0 in stage 2.0 (TID 4) in 41 ms on localhost (executor driver) (2/2)
22/12/11 19:14:13 INFO TaskSchedulerImpl: Removed TaskSet 2.0, whose tasks have all completed, from pool 
22/12/11 19:14:13 INFO DAGScheduler: ShuffleMapStage 2 (countByValue at WordCount.scala:11) finished in 0.085 s
22/12/11 19:14:13 INFO DAGScheduler: looking for newly runnable stages
22/12/11 19:14:13 INFO ReceivedBlockTracker: Deleting batches: 
22/12/11 19:14:13 INFO DAGScheduler: running: Set()
22/12/11 19:14:13 INFO DAGScheduler: waiting: Set(ResultStage 3)
22/12/11 19:14:13 INFO DAGScheduler: failed: Set()
22/12/11 19:14:13 INFO DAGScheduler: Submitting ResultStage 3 (MapPartitionsRDD[17] at setupStreams at WordCountTest.scala:6), which has no missing parents
22/12/11 19:14:13 INFO MemoryStore: Block broadcast_3 stored as values in memory (estimated size 3.6 KB, free 819.3 MB)
22/12/11 19:14:13 INFO MemoryStore: Block broadcast_3_piece0 stored as bytes in memory (estimated size 2.1 KB, free 819.3 MB)
22/12/11 19:14:13 INFO BlockManagerInfo: Added broadcast_3_piece0 in memory on envy:42281 (size: 2.1 KB, free: 819.3 MB)
22/12/11 19:14:13 INFO SparkContext: Created broadcast 3 from broadcast at DAGScheduler.scala:1184
22/12/11 19:14:13 INFO DAGScheduler: Submitting 2 missing tasks from ResultStage 3 (MapPartitionsRDD[17] at setupStreams at WordCountTest.scala:6) (first 15 tasks are for partitions Vector(0, 1))
22/12/11 19:14:13 INFO TaskSchedulerImpl: Adding task set 3.0 with 2 tasks
22/12/11 19:14:13 INFO TaskSetManager: Starting task 0.0 in stage 3.0 (TID 6, localhost, executor driver, partition 0, PROCESS_LOCAL, 7662 bytes)
22/12/11 19:14:13 INFO TaskSetManager: Starting task 1.0 in stage 3.0 (TID 7, localhost, executor driver, partition 1, ANY, 7662 bytes)
22/12/11 19:14:13 INFO Executor: Running task 1.0 in stage 3.0 (TID 7)
22/12/11 19:14:13 INFO WordCountTest: output.size = 1, numExpectedOutput = 4
22/12/11 19:14:13 INFO Executor: Running task 0.0 in stage 3.0 (TID 6)
22/12/11 19:14:13 INFO ShuffleBlockFetcherIterator: Getting 2 non-empty blocks including 2 local blocks and 0 remote blocks
22/12/11 19:14:13 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 1 ms
22/12/11 19:14:13 INFO ShuffleBlockFetcherIterator: Getting 0 non-empty blocks including 0 local blocks and 0 remote blocks
22/12/11 19:14:13 INFO FileBasedWriteAheadLog_ReceivedBlockTracker: Attempting to clear 0 old log files in file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/receivedBlockMetadata older than 0: 
22/12/11 19:14:13 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 2 ms
22/12/11 19:14:13 INFO Executor: Finished task 1.0 in stage 3.0 (TID 7). 1318 bytes result sent to driver
22/12/11 19:14:13 INFO InputInfoTracker: remove old batch metadata: 
22/12/11 19:14:13 INFO TaskSetManager: Finished task 1.0 in stage 3.0 (TID 7) in 18 ms on localhost (executor driver) (1/2)
22/12/11 19:14:13 INFO Executor: Finished task 0.0 in stage 3.0 (TID 6). 1214 bytes result sent to driver
22/12/11 19:14:13 INFO TaskSetManager: Finished task 0.0 in stage 3.0 (TID 6) in 22 ms on localhost (executor driver) (2/2)
22/12/11 19:14:13 INFO TaskSchedulerImpl: Removed TaskSet 3.0, whose tasks have all completed, from pool 
22/12/11 19:14:13 INFO DAGScheduler: ResultStage 3 (setupStreams at WordCountTest.scala:6) finished in 0.040 s
22/12/11 19:14:13 INFO DAGScheduler: Job 1 finished: setupStreams at WordCountTest.scala:6, took 0.144585 s
22/12/11 19:14:13 INFO JobScheduler: Finished job streaming job 2000 ms.0 from job set of time 2000 ms
22/12/11 19:14:13 INFO JobScheduler: Total delay: 1670775251.800 s for time 2000 ms (execution: 0.160 s)
22/12/11 19:14:13 INFO JobScheduler: Starting job streaming job 3000 ms.0 from job set of time 3000 ms
22/12/11 19:14:13 INFO ShuffledRDD: Removing RDD 3 from persistence list
22/12/11 19:14:13 INFO WordCountTest: output.size = 2, numExpectedOutput = 4
22/12/11 19:14:13 INFO SparkContext: Starting job: setupStreams at WordCountTest.scala:6
22/12/11 19:14:13 INFO MapPartitionsRDD: Removing RDD 2 from persistence list
22/12/11 19:14:13 INFO DAGScheduler: Registering RDD 11 (countByValue at WordCount.scala:11) as input to shuffle 2
22/12/11 19:14:13 INFO DAGScheduler: Got job 2 (setupStreams at WordCountTest.scala:6) with 2 output partitions
22/12/11 19:14:13 INFO DAGScheduler: Final stage: ResultStage 5 (setupStreams at WordCountTest.scala:6)
22/12/11 19:14:13 INFO DAGScheduler: Parents of final stage: List(ShuffleMapStage 4)
22/12/11 19:14:13 INFO DAGScheduler: Missing parents: List(ShuffleMapStage 4)
22/12/11 19:14:13 INFO DAGScheduler: Submitting ShuffleMapStage 4 (MapPartitionsRDD[11] at countByValue at WordCount.scala:11), which has no missing parents
22/12/11 19:14:13 INFO MapPartitionsRDD: Removing RDD 1 from persistence list
22/12/11 19:14:13 INFO MemoryStore: Block broadcast_4 stored as values in memory (estimated size 3.2 KB, free 819.3 MB)
22/12/11 19:14:13 INFO BlockManager: Removing RDD 1
22/12/11 19:14:13 INFO MemoryStore: Block broadcast_4_piece0 stored as bytes in memory (estimated size 1965.0 B, free 819.3 MB)
22/12/11 19:14:13 INFO BlockManagerInfo: Added broadcast_4_piece0 in memory on envy:42281 (size: 1965.0 B, free: 819.3 MB)
22/12/11 19:14:13 INFO ParallelCollectionRDD: Removing RDD 0 from persistence list
22/12/11 19:14:13 INFO SparkContext: Created broadcast 4 from broadcast at DAGScheduler.scala:1184
22/12/11 19:14:13 INFO DAGScheduler: Submitting 2 missing tasks from ShuffleMapStage 4 (MapPartitionsRDD[11] at countByValue at WordCount.scala:11) (first 15 tasks are for partitions Vector(0, 1))
22/12/11 19:14:13 INFO TaskSchedulerImpl: Adding task set 4.0 with 2 tasks
22/12/11 19:14:13 INFO JobGenerator: Checkpointing graph for time 2000 ms
22/12/11 19:14:13 INFO DStreamGraph: Updating checkpoint data for time 2000 ms
22/12/11 19:14:13 INFO DStreamGraph: Updated checkpoint data for time 2000 ms
22/12/11 19:14:13 INFO TaskSetManager: Starting task 0.0 in stage 4.0 (TID 8, localhost, executor driver, partition 0, PROCESS_LOCAL, 7861 bytes)
22/12/11 19:14:13 INFO TaskSetManager: Starting task 1.0 in stage 4.0 (TID 9, localhost, executor driver, partition 1, PROCESS_LOCAL, 7861 bytes)
22/12/11 19:14:13 INFO Executor: Running task 0.0 in stage 4.0 (TID 8)
22/12/11 19:14:13 INFO Executor: Running task 1.0 in stage 4.0 (TID 9)
22/12/11 19:14:13 INFO WordCountTest: output.size = 2, numExpectedOutput = 4
22/12/11 19:14:13 INFO CheckpointWriter: Submitted checkpoint of time 2000 ms to writer queue
22/12/11 19:14:13 INFO CheckpointWriter: Saving checkpoint for time 2000 ms to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000'
22/12/11 19:14:13 INFO BlockManager: Removing RDD 2
22/12/11 19:14:13 INFO BlockManager: Removing RDD 3
22/12/11 19:14:13 INFO Executor: Finished task 1.0 in stage 4.0 (TID 9). 1017 bytes result sent to driver
22/12/11 19:14:13 INFO Executor: Finished task 0.0 in stage 4.0 (TID 8). 1017 bytes result sent to driver
22/12/11 19:14:13 INFO BlockManager: Removing RDD 0
22/12/11 19:14:13 INFO TaskSetManager: Finished task 1.0 in stage 4.0 (TID 9) in 32 ms on localhost (executor driver) (1/2)
22/12/11 19:14:13 INFO TaskSetManager: Finished task 0.0 in stage 4.0 (TID 8) in 37 ms on localhost (executor driver) (2/2)
22/12/11 19:14:13 INFO TaskSchedulerImpl: Removed TaskSet 4.0, whose tasks have all completed, from pool 
22/12/11 19:14:13 INFO DAGScheduler: ShuffleMapStage 4 (countByValue at WordCount.scala:11) finished in 0.069 s
22/12/11 19:14:13 INFO DAGScheduler: looking for newly runnable stages
22/12/11 19:14:13 INFO DAGScheduler: running: Set()
22/12/11 19:14:13 INFO DAGScheduler: waiting: Set(ResultStage 5)
22/12/11 19:14:13 INFO DAGScheduler: failed: Set()
22/12/11 19:14:13 INFO DAGScheduler: Submitting ResultStage 5 (MapPartitionsRDD[18] at setupStreams at WordCountTest.scala:6), which has no missing parents
22/12/11 19:14:13 INFO WordCountTest: output.size = 2, numExpectedOutput = 4
22/12/11 19:14:14 INFO MemoryStore: Block broadcast_5 stored as values in memory (estimated size 3.6 KB, free 819.3 MB)
22/12/11 19:14:14 INFO WordCountTest: output.size = 2, numExpectedOutput = 4
22/12/11 19:14:14 INFO MemoryStore: Block broadcast_5_piece0 stored as bytes in memory (estimated size 2.1 KB, free 819.3 MB)
22/12/11 19:14:14 INFO BlockManagerInfo: Added broadcast_5_piece0 in memory on envy:42281 (size: 2.1 KB, free: 819.3 MB)
22/12/11 19:14:14 INFO SparkContext: Created broadcast 5 from broadcast at DAGScheduler.scala:1184
22/12/11 19:14:14 INFO DAGScheduler: Submitting 2 missing tasks from ResultStage 5 (MapPartitionsRDD[18] at setupStreams at WordCountTest.scala:6) (first 15 tasks are for partitions Vector(0, 1))
22/12/11 19:14:14 INFO TaskSchedulerImpl: Adding task set 5.0 with 2 tasks
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 62
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 4
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 61
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 24
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 82
22/12/11 19:14:14 INFO TaskSetManager: Starting task 0.0 in stage 5.0 (TID 10, localhost, executor driver, partition 0, ANY, 7662 bytes)
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 9
22/12/11 19:14:14 INFO TaskSetManager: Starting task 1.0 in stage 5.0 (TID 11, localhost, executor driver, partition 1, ANY, 7662 bytes)
22/12/11 19:14:14 INFO Executor: Running task 1.0 in stage 5.0 (TID 11)
22/12/11 19:14:14 INFO Executor: Running task 0.0 in stage 5.0 (TID 10)
22/12/11 19:14:14 INFO ShuffleBlockFetcherIterator: Getting 1 non-empty blocks including 1 local blocks and 0 remote blocks
22/12/11 19:14:14 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 1 ms
22/12/11 19:14:14 INFO ShuffleBlockFetcherIterator: Getting 1 non-empty blocks including 1 local blocks and 0 remote blocks
22/12/11 19:14:14 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 2 ms
22/12/11 19:14:14 INFO Executor: Finished task 1.0 in stage 5.0 (TID 11). 1318 bytes result sent to driver
22/12/11 19:14:14 INFO BlockManagerInfo: Removed broadcast_0_piece0 on envy:42281 in memory (size: 1970.0 B, free: 819.3 MB)
22/12/11 19:14:14 INFO Executor: Finished task 0.0 in stage 5.0 (TID 10). 1318 bytes result sent to driver
22/12/11 19:14:14 INFO TaskSetManager: Finished task 0.0 in stage 5.0 (TID 10) in 39 ms on localhost (executor driver) (1/2)
22/12/11 19:14:14 INFO TaskSetManager: Finished task 1.0 in stage 5.0 (TID 11) in 41 ms on localhost (executor driver) (2/2)
22/12/11 19:14:14 INFO TaskSchedulerImpl: Removed TaskSet 5.0, whose tasks have all completed, from pool 
22/12/11 19:14:14 INFO DAGScheduler: ResultStage 5 (setupStreams at WordCountTest.scala:6) finished in 0.141 s
22/12/11 19:14:14 INFO WordCountTest: output.size = 2, numExpectedOutput = 4
22/12/11 19:14:14 INFO DAGScheduler: Job 2 finished: setupStreams at WordCountTest.scala:6, took 0.232520 s
22/12/11 19:14:14 INFO JobScheduler: Finished job streaming job 3000 ms.0 from job set of time 3000 ms
22/12/11 19:14:14 INFO JobScheduler: Total delay: 1670775251.063 s for time 3000 ms (execution: 0.262 s)
22/12/11 19:14:14 INFO JobScheduler: Starting job streaming job 4000 ms.0 from job set of time 4000 ms
22/12/11 19:14:14 INFO ShuffledRDD: Removing RDD 8 from persistence list
22/12/11 19:14:14 INFO MapPartitionsRDD: Removing RDD 7 from persistence list
22/12/11 19:14:14 INFO SparkContext: Starting job: setupStreams at WordCountTest.scala:6
22/12/11 19:14:14 INFO MapPartitionsRDD: Removing RDD 5 from persistence list
22/12/11 19:14:14 INFO DAGScheduler: Registering RDD 15 (countByValue at WordCount.scala:11) as input to shuffle 3
22/12/11 19:14:14 INFO ParallelCollectionRDD: Removing RDD 4 from persistence list
22/12/11 19:14:14 INFO DAGScheduler: Got job 3 (setupStreams at WordCountTest.scala:6) with 2 output partitions
22/12/11 19:14:14 INFO DAGScheduler: Final stage: ResultStage 7 (setupStreams at WordCountTest.scala:6)
22/12/11 19:14:14 INFO DAGScheduler: Parents of final stage: List(ShuffleMapStage 6)
22/12/11 19:14:14 INFO DAGScheduler: Missing parents: List(ShuffleMapStage 6)
22/12/11 19:14:14 INFO BlockManager: Removing RDD 8
22/12/11 19:14:14 INFO DAGScheduler: Submitting ShuffleMapStage 6 (MapPartitionsRDD[15] at countByValue at WordCount.scala:11), which has no missing parents
22/12/11 19:14:14 INFO BlockManager: Removing RDD 7
22/12/11 19:14:14 INFO BlockManager: Removing RDD 5
22/12/11 19:14:14 INFO WordCountTest: output.size = 3, numExpectedOutput = 4
22/12/11 19:14:14 INFO BlockManager: Removing RDD 4
22/12/11 19:14:14 INFO MemoryStore: Block broadcast_6 stored as values in memory (estimated size 3.2 KB, free 819.3 MB)
22/12/11 19:14:14 INFO JobGenerator: Checkpointing graph for time 3000 ms
22/12/11 19:14:14 INFO DStreamGraph: Updating checkpoint data for time 3000 ms
22/12/11 19:14:14 INFO DStreamGraph: Updated checkpoint data for time 3000 ms
22/12/11 19:14:14 INFO WordCountTest: output.size = 3, numExpectedOutput = 4
22/12/11 19:14:14 INFO CheckpointWriter: Submitted checkpoint of time 3000 ms to writer queue
22/12/11 19:14:14 INFO MemoryStore: Block broadcast_6_piece0 stored as bytes in memory (estimated size 1971.0 B, free 819.3 MB)
22/12/11 19:14:14 INFO BlockManagerInfo: Added broadcast_6_piece0 in memory on envy:42281 (size: 1971.0 B, free: 819.3 MB)
22/12/11 19:14:14 INFO SparkContext: Created broadcast 6 from broadcast at DAGScheduler.scala:1184
22/12/11 19:14:14 INFO DAGScheduler: Submitting 2 missing tasks from ShuffleMapStage 6 (MapPartitionsRDD[15] at countByValue at WordCount.scala:11) (first 15 tasks are for partitions Vector(0, 1))
22/12/11 19:14:14 INFO TaskSchedulerImpl: Adding task set 6.0 with 2 tasks
22/12/11 19:14:14 INFO TaskSetManager: Starting task 0.0 in stage 6.0 (TID 12, localhost, executor driver, partition 0, PROCESS_LOCAL, 7861 bytes)
22/12/11 19:14:14 INFO TaskSetManager: Starting task 1.0 in stage 6.0 (TID 13, localhost, executor driver, partition 1, PROCESS_LOCAL, 7869 bytes)
22/12/11 19:14:14 INFO Executor: Running task 0.0 in stage 6.0 (TID 12)
22/12/11 19:14:14 INFO Executor: Running task 1.0 in stage 6.0 (TID 13)
22/12/11 19:14:14 INFO Executor: Finished task 0.0 in stage 6.0 (TID 12). 1017 bytes result sent to driver
22/12/11 19:14:14 INFO TaskSetManager: Finished task 0.0 in stage 6.0 (TID 12) in 25 ms on localhost (executor driver) (1/2)
22/12/11 19:14:14 INFO Executor: Finished task 1.0 in stage 6.0 (TID 13). 1060 bytes result sent to driver
22/12/11 19:14:14 INFO TaskSetManager: Finished task 1.0 in stage 6.0 (TID 13) in 29 ms on localhost (executor driver) (2/2)
22/12/11 19:14:14 INFO TaskSchedulerImpl: Removed TaskSet 6.0, whose tasks have all completed, from pool 
22/12/11 19:14:14 INFO WordCountTest: output.size = 3, numExpectedOutput = 4
22/12/11 19:14:14 INFO DAGScheduler: ShuffleMapStage 6 (countByValue at WordCount.scala:11) finished in 0.126 s
22/12/11 19:14:14 INFO DAGScheduler: looking for newly runnable stages
22/12/11 19:14:14 INFO DAGScheduler: running: Set()
22/12/11 19:14:14 INFO DAGScheduler: waiting: Set(ResultStage 7)
22/12/11 19:14:14 INFO DAGScheduler: failed: Set()
22/12/11 19:14:14 INFO DAGScheduler: Submitting ResultStage 7 (MapPartitionsRDD[19] at setupStreams at WordCountTest.scala:6), which has no missing parents
22/12/11 19:14:14 INFO MemoryStore: Block broadcast_7 stored as values in memory (estimated size 3.6 KB, free 819.3 MB)
22/12/11 19:14:14 INFO MemoryStore: Block broadcast_7_piece0 stored as bytes in memory (estimated size 2.1 KB, free 819.3 MB)
22/12/11 19:14:14 INFO BlockManagerInfo: Added broadcast_7_piece0 in memory on envy:42281 (size: 2.1 KB, free: 819.3 MB)
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 1
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 48
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 36
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 17
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 78
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 57
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 91
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 13
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 85
22/12/11 19:14:14 INFO SparkContext: Created broadcast 7 from broadcast at DAGScheduler.scala:1184
22/12/11 19:14:14 INFO DAGScheduler: Submitting 2 missing tasks from ResultStage 7 (MapPartitionsRDD[19] at setupStreams at WordCountTest.scala:6) (first 15 tasks are for partitions Vector(0, 1))
22/12/11 19:14:14 INFO TaskSchedulerImpl: Adding task set 7.0 with 2 tasks
22/12/11 19:14:14 INFO CheckpointWriter: Checkpoint for time 2000 ms saved to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000', took 3963 bytes and 390 ms
22/12/11 19:14:14 INFO WordCountTest: output.size = 3, numExpectedOutput = 4
22/12/11 19:14:14 INFO DStreamGraph: Clearing checkpoint data for time 2000 ms
22/12/11 19:14:14 INFO DStreamGraph: Cleared checkpoint data for time 2000 ms
22/12/11 19:14:14 INFO ReceivedBlockTracker: Deleting batches: 
22/12/11 19:14:14 INFO FileBasedWriteAheadLog_ReceivedBlockTracker: Attempting to clear 0 old log files in file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/receivedBlockMetadata older than 1000: 
22/12/11 19:14:14 INFO InputInfoTracker: remove old batch metadata: 
22/12/11 19:14:14 INFO CheckpointWriter: Saving checkpoint for time 3000 ms to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000'
22/12/11 19:14:14 INFO TaskSetManager: Starting task 0.0 in stage 7.0 (TID 14, localhost, executor driver, partition 0, ANY, 7662 bytes)
22/12/11 19:14:14 INFO TaskSetManager: Starting task 1.0 in stage 7.0 (TID 15, localhost, executor driver, partition 1, ANY, 7662 bytes)
22/12/11 19:14:14 INFO Executor: Running task 0.0 in stage 7.0 (TID 14)
22/12/11 19:14:14 INFO Executor: Running task 1.0 in stage 7.0 (TID 15)
22/12/11 19:14:14 INFO WordCountTest: output.size = 3, numExpectedOutput = 4
22/12/11 19:14:14 INFO ShuffleBlockFetcherIterator: Getting 2 non-empty blocks including 2 local blocks and 0 remote blocks
22/12/11 19:14:14 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 1 ms
22/12/11 19:14:14 INFO BlockManagerInfo: Removed broadcast_3_piece0 on envy:42281 in memory (size: 2.1 KB, free: 819.3 MB)
22/12/11 19:14:14 INFO ShuffleBlockFetcherIterator: Getting 1 non-empty blocks including 1 local blocks and 0 remote blocks
22/12/11 19:14:14 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 3 ms
22/12/11 19:14:14 INFO Executor: Finished task 1.0 in stage 7.0 (TID 15). 1318 bytes result sent to driver
22/12/11 19:14:14 INFO TaskSetManager: Finished task 1.0 in stage 7.0 (TID 15) in 69 ms on localhost (executor driver) (1/2)
22/12/11 19:14:14 INFO Executor: Finished task 0.0 in stage 7.0 (TID 14). 1318 bytes result sent to driver
22/12/11 19:14:14 INFO TaskSetManager: Finished task 0.0 in stage 7.0 (TID 14) in 84 ms on localhost (executor driver) (2/2)
22/12/11 19:14:14 INFO TaskSchedulerImpl: Removed TaskSet 7.0, whose tasks have all completed, from pool 
22/12/11 19:14:14 INFO WordCountTest: output.size = 3, numExpectedOutput = 4
22/12/11 19:14:14 INFO CheckpointWriter: Checkpoint for time 3000 ms saved to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000', took 3987 bytes and 101 ms
22/12/11 19:14:14 INFO DStreamGraph: Clearing checkpoint data for time 3000 ms
22/12/11 19:14:14 INFO DStreamGraph: Cleared checkpoint data for time 3000 ms
22/12/11 19:14:14 INFO ReceivedBlockTracker: Deleting batches: 
22/12/11 19:14:14 INFO DAGScheduler: ResultStage 7 (setupStreams at WordCountTest.scala:6) finished in 0.144 s
22/12/11 19:14:14 INFO FileBasedWriteAheadLog_ReceivedBlockTracker: Attempting to clear 0 old log files in file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/receivedBlockMetadata older than 2000: 
22/12/11 19:14:14 INFO InputInfoTracker: remove old batch metadata: 1000 ms
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 32
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 2
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 20
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 84
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 27
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 81
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 89
22/12/11 19:14:14 INFO DAGScheduler: Job 3 finished: setupStreams at WordCountTest.scala:6, took 0.301190 s
22/12/11 19:14:14 INFO JobScheduler: Finished job streaming job 4000 ms.0 from job set of time 4000 ms
22/12/11 19:14:14 INFO JobScheduler: Total delay: 1670775250.381 s for time 4000 ms (execution: 0.317 s)
22/12/11 19:14:14 INFO ShuffledRDD: Removing RDD 12 from persistence list
22/12/11 19:14:14 INFO MapPartitionsRDD: Removing RDD 11 from persistence list
22/12/11 19:14:14 INFO BlockManager: Removing RDD 12
22/12/11 19:14:14 INFO MapPartitionsRDD: Removing RDD 10 from persistence list
22/12/11 19:14:14 INFO BlockManagerInfo: Removed broadcast_2_piece0 on envy:42281 in memory (size: 1970.0 B, free: 819.3 MB)
22/12/11 19:14:14 INFO BlockManager: Removing RDD 11
22/12/11 19:14:14 INFO ParallelCollectionRDD: Removing RDD 9 from persistence list
22/12/11 19:14:14 INFO BlockManager: Removing RDD 10
22/12/11 19:14:14 INFO BlockManager: Removing RDD 9
22/12/11 19:14:14 INFO JobGenerator: Checkpointing graph for time 4000 ms
22/12/11 19:14:14 INFO DStreamGraph: Updating checkpoint data for time 4000 ms
22/12/11 19:14:14 INFO DStreamGraph: Updated checkpoint data for time 4000 ms
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 102
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 88
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 69
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 53
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 56
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 60
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 14
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 74
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 41
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 45
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 77
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 87
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 38
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 70
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 42
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 64
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 10
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 3
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 51
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 22
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 59
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 96
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 39
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 11
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 26
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 46
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 101
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 67
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 12
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 35
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 99
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 94
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 33
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 72
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 54
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 23
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 49
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 44
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 40
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 55
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 58
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 68
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 29
22/12/11 19:14:14 INFO CheckpointWriter: Saving checkpoint for time 4000 ms to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000'
22/12/11 19:14:14 INFO CheckpointWriter: Submitted checkpoint of time 4000 ms to writer queue
22/12/11 19:14:14 INFO BlockManagerInfo: Removed broadcast_1_piece0 on envy:42281 in memory (size: 2.1 KB, free: 819.3 MB)
22/12/11 19:14:14 INFO WordCountTest: Output generated in 1969 milliseconds
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 86
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 71
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 75
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 100
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 18
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 80
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 63
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 28
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 21
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 25
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 83
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 66
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 98
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 30
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 97
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 7
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 37
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 103
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 79
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 76
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 65
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 73
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 90
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 93
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 16
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 34
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 50
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 8
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 47
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 52
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 19
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 43
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 95
22/12/11 19:14:14 INFO WordCountTest: [WrappedArray(),WrappedArray((a,2))]
22/12/11 19:14:14 INFO WordCountTest: [WrappedArray(),WrappedArray((a,3))]
22/12/11 19:14:14 INFO WordCountTest: [WrappedArray((b,1)),WrappedArray((a,1))]
22/12/11 19:14:14 INFO WordCountTest: [WrappedArray((b,2)),WrappedArray((a,2))]
22/12/11 19:14:14 INFO CheckpointWriter: Checkpoint for time 4000 ms saved to file 'file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/checkpoint-4000', took 3995 bytes and 27 ms
22/12/11 19:14:14 INFO DStreamGraph: Clearing checkpoint data for time 4000 ms
22/12/11 19:14:14 INFO DStreamGraph: Cleared checkpoint data for time 4000 ms
22/12/11 19:14:14 INFO ReceivedBlockTracker: Deleting batches: 
22/12/11 19:14:14 INFO FileBasedWriteAheadLog_ReceivedBlockTracker: Attempting to clear 0 old log files in file:/tmp/spark-cddf612f-25ef-4748-95d4-ad95f683201f/receivedBlockMetadata older than 3000: 
22/12/11 19:14:14 INFO InputInfoTracker: remove old batch metadata: 2000 ms
22/12/11 19:14:14 INFO ContextCleaner: Cleaned shuffle 0
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 0
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 15
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 5
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 92
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 6
22/12/11 19:14:14 INFO ContextCleaner: Cleaned accumulator 31
22/12/11 19:14:14 INFO BatchedWriteAheadLog: BatchedWriteAheadLog shutting down at time: 1670775254563.
22/12/11 19:14:14 WARN BatchedWriteAheadLog: BatchedWriteAheadLog Writer queue interrupted.
22/12/11 19:14:14 INFO BatchedWriteAheadLog: BatchedWriteAheadLog Writer thread exiting.
22/12/11 19:14:14 INFO FileBasedWriteAheadLog_ReceivedBlockTracker: Stopped write ahead log manager
22/12/11 19:14:14 INFO ReceiverTracker: ReceiverTracker stopped
22/12/11 19:14:14 INFO JobGenerator: Stopping JobGenerator immediately
22/12/11 19:14:14 INFO RecurringTimer: Stopped timer for JobGenerator after time 4000
22/12/11 19:14:14 INFO CheckpointWriter: CheckpointWriter executor terminated? true, waited for 0 ms.
22/12/11 19:14:14 INFO JobGenerator: Stopped JobGenerator
22/12/11 19:14:14 INFO JobScheduler: Stopped JobScheduler
22/12/11 19:14:14 INFO StreamingContext: StreamingContext stopped successfully
22/12/11 19:14:14 INFO SparkUI: Stopped Spark web UI at http://envy:4040
22/12/11 19:14:14 INFO MapOutputTrackerMasterEndpoint: MapOutputTrackerMasterEndpoint stopped!
22/12/11 19:14:14 INFO MemoryStore: MemoryStore cleared
22/12/11 19:14:14 INFO BlockManager: BlockManager stopped
22/12/11 19:14:14 INFO BlockManagerMaster: BlockManagerMaster stopped
22/12/11 19:14:14 INFO OutputCommitCoordinator$OutputCommitCoordinatorEndpoint: OutputCommitCoordinator stopped!
22/12/11 19:14:14 INFO SparkContext: Successfully stopped SparkContext
22/12/11 19:14:14 INFO WordCountTest: --------------------------------
22/12/11 19:14:14 INFO WordCountTest: output.size = 4
22/12/11 19:14:14 INFO WordCountTest: output
22/12/11 19:14:14 INFO WordCountTest: [(a,2)]
22/12/11 19:14:14 INFO WordCountTest: [(a,3)]
22/12/11 19:14:14 INFO WordCountTest: [(b,1),(a,1)]
22/12/11 19:14:14 INFO WordCountTest: [(b,2),(a,2)]
22/12/11 19:14:14 INFO WordCountTest: expected output.size = 4
22/12/11 19:14:14 INFO WordCountTest: expected output
22/12/11 19:14:14 INFO WordCountTest: [(a,2)]
22/12/11 19:14:14 INFO WordCountTest: [(a,3)]
22/12/11 19:14:14 INFO WordCountTest: [(a,1),(b,1)]
22/12/11 19:14:14 INFO WordCountTest: [(b,2),(a,2)]
22/12/11 19:14:14 INFO WordCountTest: --------------------------------
22/12/11 19:14:14 INFO WordCountTest: Output verified successfully
22/12/11 19:14:14 WARN StreamingContext: StreamingContext has already been stopped
22/12/11 19:14:14 INFO SparkContext: SparkContext already stopped.
22/12/11 19:14:14 INFO WordCountTest: 

===== FINISHED ok.ml.WordCountTest: 'WordCount basic' =====

22/12/11 19:14:14 WARN WordCountTest: 

===== POSSIBLE THREAD LEAK IN SUITE ok.ml.WordCountTest, thread names: rpc-boss-3-1, shuffle-boss-6-1 =====
 -->
```bash
[info] WordCountTest:
[info] - WordCount basic
[info] Run completed in 5 seconds, 978 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 8 s, completed Dec 11, 2022 7:14:14 PM
```

[^1]: To use `**` in `bash` command enable `globstar` shell option: `$ shopt -s globstar`