import org.apache.spark.sql.SparkSession;

public class SparkUtil {

    public static SparkSession getSparkSession(String appName) {
        return SparkSession
                .builder()
                .appName(appName)
                .config("spark.sql.warehouse.dir", "hdfs://ns1/apps/hive/warehouse/dbname.db")
                .config("spark.sql.shuffle.partitions", 1024) // 3 * num-executors * executor-cores
                .config("spark.driver.maxResultSize", "10g")
                .enableHiveSupport()
                .getOrCreate();
    }

}
