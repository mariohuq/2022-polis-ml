package ok.ml

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object Main {
  def main(args: Array[String]): Unit = {
    import spark.sqlContext.implicits._

    def wordCount(df: DataFrame): DataFrame = df
      .select(concat_ws(" ", $"class", $"comment") as "text")
      .select(split($"text", "\\s") as "words")
      .select(explode($"words") as "word")
      .groupBy($"word").count()

    val Array(input, output) = args
    val spark = SparkSession.builder().getOrCreate()
    wordCount(spark.read.option("header", "true").csv(input))
      .write.mode("overwrite").csv(output)
    spark.stop()
  }
}