import util.SparkUtil;
import org.apache.spark.sql.SparkSession;

public class JobRunner {
/*

spark-submit \
--class JobRunner \
--driver-memory 10G \
--num-executors 20 \
--executor-memory 2G \
--executor-cores 5 \
--master yarn \
--queue ai PackageName.jar -configPackage \
> out.log

*/
    public static void main(String[] args) throws Exception {
        try{
            SparkSession session = SparkUtil.getSparkSession("NAME_SESSION");

            session.sparkContext().setLogLevel("ERROR");
            session.sqlContext().setConf("hive.exec.dynamic.partition", "true");
            session.sqlContext().setConf("hive.exec.dynamic.partition.mode", "nonstrict");

            String config = args[0];
            ClassExecute ce = new ClassExecute(session);
            ce.execute(config);
            session.stop();

        }catch (Exception e){
            throw new Exception("exception occur:",e);
        }
    }
}
