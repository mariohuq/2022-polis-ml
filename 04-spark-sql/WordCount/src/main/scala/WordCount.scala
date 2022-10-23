package ok.ml

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object WordCount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().getOrCreate()
    import spark.sqlContext.implicits._
    def wordCount(df: DataFrame, stopWords: Broadcast[Set[String]]): DataFrame = df
      .select(concat_ws(" ", $"class", $"comment") as "text")
      .select(split($"text", """(\s|\p{Punct})+""") as "words")
      .select(explode($"words") as "word")
      .select(lower($"word") as "word")
      .filter(row => !stopWords.value.contains(row.getString(0)))
      .groupBy($"word").count().as("count")
      .select($"count", $"word")
      .sort($"count".desc)

    val Array(input, output, stopList) = args
    val stopWords = spark.read
      .text(stopList)
      .select(lower($"value"))
      .as[String]
      .collect()
      .toSet
    wordCount(
      spark.read.option("header", "true").csv(input),
      spark.sparkContext.broadcast(stopWords)
    ).write.mode("overwrite").csv(output)
    spark.stop()
  }
}