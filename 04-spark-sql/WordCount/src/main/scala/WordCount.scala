package ok.ml

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object WordCount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().getOrCreate()
    import spark.sqlContext.implicits._
    def wordCount(df: DataFrame): DataFrame = df
      .select(concat_ws(" ", $"class", $"comment") as "text")
      .select(split($"text", """(\s|\p{Punct})+""") as "words")
      .select(explode($"words") as "word")
      .select(lower($"word") as "word")
      .groupBy($"word").count()

    val Array(input, output) = args
    wordCount(spark.read.option("header", "true").csv(input))
      .write.mode("overwrite").csv(output)
    spark.stop()
  }
}