package ok.ml

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object WordCount {
  def main(args: Array[String]): Unit = {
    def wordCount(lines: RDD[String], stopWords: Set[String]): RDD[String] = lines
      .flatMap(_.split("""(\s|\p{Punct})+"""))
      .map(_.toLowerCase)
      .filter(!stopWords.contains(_))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .sortBy{case (_, count) => count }
      .map{ case (word, count) => s"$count\t$word"}

    val Array(input, output, stopList) = args
    val sc = new SparkContext()
    val stopWords = sc.textFile(stopList)
      .map(_.toLowerCase)
      .collect
      .toSet
    wordCount(sc.textFile(input), stopWords).saveAsTextFile(output)
    sc.stop()
  }
}