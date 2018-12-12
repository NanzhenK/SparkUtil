import org.apache.spark.sql.*;

public class ClassExecute implements Serializable{
    private static final String SQL="";

    private SparkSession session;

    public ClassExecute(SparkSession session) {
        this.session = session;
    }

    public void execute() {
        try {
            Dataset<Row> dataset = session.sql(SQL);
            WindowSpec kw = Window.partitionBy("keyword").orderBy("keyword"); //分区标记
            dataset
                .mapPartitions((row)->{} , Encoders.kryo(ClassName.class)) //kryo用于自定义Serializable的class
                .withColumn("value",split(col("value"),"\\|")) //分割符号前\\转义
                .select(
                    col("value").getItem(0).as("keyword"),
                    col("value").getItem(1).as("count").cast("Int")
                )
                .withColumn("isrepeat", when(row_number().over(kw).equalTo(1),1).otherwise(0))//定义重复数据中第一项为1，其余为0
            dataset.write().mode(SaveMode.Overwrite).format("hive")
                    .insertInto("schemaName.dbName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
