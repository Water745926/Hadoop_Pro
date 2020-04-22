import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @description:Spark对象工具类
 * @author: lijiayu
 * @create: 2020-03-22 22:08
 */
object Spark_role {
  //测试用例
  def InitSparkContext(Appname: String): SparkContext = {
    // 1 接受程序参数
    // 2 创建sparkconf->sparkContext
    val sparkConf = new SparkConf()
    sparkConf.setAppName(Appname)
    sparkConf.setMaster("local[*]")
    // RDD 序列化到磁盘 worker与worker之间的数据传输
    sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(sparkConf)
    Logger.getRootLogger.setLevel(Level.ERROR)

    sc

  }

  def InitSparkSession(dateStr: String, nameStr: String): SparkSession = {
    val spark = SparkSession
      .builder()
      .master("yarn")
//      .master("local[*]")
      .appName(dateStr + "<=***=>" + nameStr)
      .config("spark.network.timeout", "1000")
      .config("spark.rpc.askTimeout ", "1000")
      .config("spark.rpc.lookupTimeout ", "1000")
      .config("spark.shuffle.blockTransferService", "nio")
      .config("spark.reducer.maxSizeInFlight", 96)
      .config("spark.shuffle.compress", true)
      .config("spark.shuffle.file.buffer", 64)
      .config("spark.shuffle.io.maxRetries", 6)
      .config("spark.shuffle.io.retryWait", 10)
      .config("spark.shuffle.io.preferDirectBufs", true)
      .config("spark.shuffle.sort.bypassMergeThreshold", 400)
      .config("spark.shuffle.spill.compress", true)
      .config("spark.shuffle.memoryFraction", 0.4)
      .config("spark.shuffle.consolidateFiles", true)
      //      .config("spark.shuffle.manager", "HashShuffleManager")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .config("spark.storage.memoryFraction", 0.3)
      .config("spark.sql.autoBroadcastJoinThreshold", 50485760)
      //      .config("spark.shuffle.manager"->"tungsten-sort")
      //      .config("spark.locality.wait.process"->3)
      //      .config("spark.locality.wait.node"->3)
      //      .config("spark.locality.wait.rack"->3)
      //      .conf("spark.core.connection.ack.wait.timeout"->120)
      //      JVM调优，永久带内存溢出，--conf spark.driver.extraJavaOptions="-XX:PermSize=128M -XX:MaxPermSize=256M"
      //      对连接超时设置，--conf spark.core.connection.ack.wait.timeout=120 在脚本中添加，通过set或者config没用
      //      .conf("spark.yarn.executor.memoryOverhea"->2048)
      //      对外内存参数设置，需要--conf spark.yarn.executor.memoryOverhead=2048 在脚本中添加，通过set或者config没用
      //      .enableHiveSupport()
      .enableHiveSupport()
      .getOrCreate()
    spark.conf.set("spark.shuffle.sort.bypassMergeThreshold", "2000")
    spark.conf.set("spark.sql.shuffle.partitions", "200")
    spark.conf.set("spark.default.parallelism", "200")

    spark.sql("set hive.exec.dynamic.partition=true")
    spark.sql("set hive.exec.dynamic.partition.mode=nonstrict")
    //    spark.sql("set hive.exec.max.dynamic.partitions.pernode=1000000") //默认100
    //    spark.sql("set hive.exec.max.dynamic.partitions=1000000") //默认值
    //    spark.sql("set hive.exec.max.created.files=1000000") //默认值
    spark.sql("set hive.exec.compress.intermediate=true") //开启中间压缩，默认false
    spark.sql("set hive.exec.compress.output=true") //最后map或者reduce-task压缩开启
    spark.sql("set hive.map.aggr=true") //map端聚合
    spark.sql("set hive.merge.mapredfiles=true") //在一个map/reduce作业结束后合并小文件，默认不开启false)
    //    spark.sql("set hive.exec.reducers.bytes.per.reducer=512000000") //每个reducer的大小，默认是1G，输入文件如果是10G，那么就会起10个reducer；"))
    //    spark.sql("set mapred.reduce.tasks=-1") //设置为-1负数，使得下面的配置reducetask最大数量生效。
    //    spark.sql("set hive.exec.reducers.max=3000") //最大reduce个数，sparksql中被弃用用下面的参数
    spark.sql("set spark.sql.shuffle.partitions = 1000")
    spark.sql("set hive.optimize.skewjoin=true")
    spark.sql("set mapred.job.reuse.jvm.num.tasks=80") //jvm重用次数
    spark.sql("set hive.groupby.skewindata=true") //负载均衡,默认false数据倾斜优化
    spark.sql("set hive.map.aggr.hash.min.reduction=1") //：如果hash表的容量与输入行数之比超过这个数，那么map端的hash聚合将被关闭，默认是0.5，设置为1可以保证hash聚合永不被关闭
    //    spark.sql("set hive.optimize.ppd=true") //谓词下推
    spark.sql("set hive.exec.parallel=true") //job并行执行
    spark.sql("set hive.exec.parallel.thread.number=16") //并行度
    spark.sql("set mapred.max.split.size=128000000") //map任务个数决定因素:文件个数，文件大小，块大小
    spark.sql("set mapred.min.split.size.per.node=128000000")
    spark.sql("set mapred.min.split.size.per.rack=128000000")
    spark.sql("set hive.input.format=org.apache.hadoop.hive.ql.io.CombineHiveInputFormat") //当map过多时候按照上面的规则把小文件合并
    //    spark.sql("set mapred.map.tasks=5000")//map每个数据很大时候，慢，加大map数
    //    spark.sql("set hive.exec.reducers.bytes.per.reducer =3000 ")//干预reduce个数计算规则，增大或者减少reduce个数
    spark.sql("set hive.map.aggr=true") //开启map端合并聚合，注意场景
    Logger.getRootLogger.setLevel(Level.ERROR)
    spark
  }
}

