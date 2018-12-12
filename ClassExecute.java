import org.apache.spark.sql.*;

public class ClassExecute {
    private static final String SQL="";

    private SparkSession session;

    public ClassExecute(SparkSession session) {
        this.session = session;
    }

    public void execute() {
        try {
            Dataset<Row> dataset = session.sql(SQL);
            dataset.write().mode(SaveMode.Overwrite).format("hive")
                    .insertInto("schemaName.dbName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
