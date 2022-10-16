package ok.ml

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object WordCount {
  def main(args: Array[String]): Unit = {
    def wordCount(lines: RDD[String]): RDD[(Int, String)] = lines
      .flatMap(_.split("""(\s|\p{Punct})+"""))
      .map(word => (word.toLowerCase, 1))
      .reduceByKey(_ + _)
      .map{ case (word, count) => (count, word) }
      .sortByKey()

    val Array(input, output) = args
    val sc = new SparkContext()
    wordCount(sc.textFile(input)).saveAsTextFile(output)
    sc.stop()
  }
}