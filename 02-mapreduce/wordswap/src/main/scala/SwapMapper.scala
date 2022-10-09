package ok.ml

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

class SwapMapper extends Mapper[LongWritable, Text, LongWritable, Text] {
  val word = new Text()
  val amount = new LongWritable()

  override def map(key: LongWritable, value: Text,
                   context: Mapper[LongWritable, Text, LongWritable, Text]#Context): Unit = {
    val Array(w, c) = value.toString.split("\\s", 2)
    word.set(w)
    amount.set(c.toLong)
    context.write(amount, word)
  }
}
