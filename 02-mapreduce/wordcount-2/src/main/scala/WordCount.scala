package ok.ml

import org.apache.hadoop.conf.{Configuration, Configured}
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer
import org.apache.hadoop.mapreduce.{Job, Mapper, Partitioner}
import org.apache.hadoop.util.{Tool, ToolRunner}

class Tokenizer extends Mapper[Object, Text, Text, IntWritable] {
  val one = new IntWritable(1)
  val text = new Text()

  override def map(key: Object, value: Text, context: Mapper[Object, Text, Text, IntWritable]#Context): Unit = {
    for (word <- value.toString.trim.toLowerCase.split("\\s")) {
      text.set(word)
      context.write(text, one)
    }
  }
}

class WordLenPartitioner extends Partitioner[Text, IntWritable] {
  override def getPartition(key: Text, value: IntWritable, numPartitions: Int): Int = numPartitions match {
    case 1 => 0
    case _ => if (key.getLength > 2) 1 else 0
  }
}

class WordCount extends Configured with Tool {
  val IN_PATH_PARAM = "wordcount.input"
  val OUT_PATH_PARAM = "wordcount.output"

  override def run(args: Array[String]): Int = {
    val job = Job.getInstance(getConf, "Word Count")
    job.setJarByClass(getClass)
    job.setOutputKeyClass(classOf[Text])
    job.setOutputValueClass(classOf[IntWritable])
    job.setMapperClass(classOf[Tokenizer])
    // use existing reducer
    job.setReducerClass(classOf[IntSumReducer[Text]])
    job.setNumReduceTasks(2)
    job.setPartitionerClass(classOf[WordLenPartitioner])
    FileInputFormat.addInputPath(job, new Path(getConf.get(IN_PATH_PARAM)))
    FileOutputFormat.setOutputPath(job, new Path(getConf.get(OUT_PATH_PARAM)))
    if (job.waitForCompletion(true)) 0 else 1
  }
}

object WordCount {
  def main(args: Array[String]): Unit =
    System.exit(ToolRunner.run(new Configuration, new WordCount, args))
}