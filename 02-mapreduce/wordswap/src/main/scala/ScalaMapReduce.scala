package ok.ml

import org.apache.hadoop.conf.{Configuration, Configured}
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.{Job, Reducer}
import org.apache.hadoop.util.{Tool, ToolRunner}

class ScalaMapReduce extends Configured with Tool {
  val IN_PATH_PARAM = "swap.input"
  val OUT_PATH_PARAM = "swap.output"

  override def run(args: Array[String]): Int = {
    val job = Job.getInstance(getConf, "Word Count")
    job.setJarByClass(getClass)
    job.setOutputKeyClass(classOf[LongWritable])
    job.setOutputValueClass(classOf[Text])
    job.setMapperClass(classOf[SwapMapper])
    job.setReducerClass(classOf[Reducer[LongWritable, Text, LongWritable, Text]])
    job.setNumReduceTasks(1)
    FileInputFormat.addInputPath(job, new Path(getConf.get(IN_PATH_PARAM)))
    FileOutputFormat.setOutputPath(job, new Path(getConf.get(OUT_PATH_PARAM)))
    if (job.waitForCompletion(true)) 0 else 1
  }
}

object ScalaMapReduce {
  def main(args: Array[String]): Unit =
    System.exit(ToolRunner.run(new Configuration, new ScalaMapReduce, args))
}