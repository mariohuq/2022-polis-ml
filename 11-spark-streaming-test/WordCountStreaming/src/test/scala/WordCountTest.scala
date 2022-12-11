package ok.ml

import org.apache.spark.streaming.TestSuiteBase
import org.apache.spark.streaming.dstream.DStream

class WordCountTest extends TestSuiteBase {
  test("WordCount basic") {
    testOperation(
      Seq(
        Seq("a", "a"),
        Seq("a a", "a"),
        Seq("a", "b"),
        Seq("a", "a   b", "b")
      ),
      (s: DStream[String]) => WordCount(s),
      Seq(
        Seq(("a", 2L)),
        Seq(("a", 3L)),
        Seq(("a", 1L),("b", 1L)),
        Seq(("b", 2L), ("a", 2L))),
      useSet = true
    )
  }
}