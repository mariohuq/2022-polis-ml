package ok.ml

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object WordCount {
  def main(args: Array[String]): Unit = {
    def wordCount(lines: RDD[String]): RDD[(String, Int)] = lines
      .flatMap(_.split("""(\s|\p{Punct})+"""))
      .map(word => (word.toLowerCase, 1))
      .reduceByKey(_ + _)
      .sortBy { case (_, count) => count }

    val Array(input, output) = args
    val sc = new SparkContext()
    wordCount(sc.textFile(input)).saveAsTextFile(output)
    sc.stop()
  }
}