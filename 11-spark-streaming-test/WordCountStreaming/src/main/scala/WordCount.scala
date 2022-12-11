package ok.ml

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WordCount {
  def apply(lines: DStream[String]): DStream[(String, Long)] = lines
    .flatMap(_.split("\\s+"))
    .countByValue() // it is exactly map to (word, 1L) and reduce by key with addition

  def main(args: Array[String]): Unit = {
    Logger.getRootLogger.setLevel(Level.ERROR)

    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("WordCount")
    val ssc = new StreamingContext(conf, batchDuration = Seconds(2))
    val input = ssc.socketTextStream(hostname = "localhost", port = 9999)
    WordCount(input).print
    ssc.start
    ssc.awaitTermination
  }
}
