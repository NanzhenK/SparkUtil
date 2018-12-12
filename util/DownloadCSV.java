package spark2csv.languages;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.nio.charset.Charset;

public class DownloadCSV {

    private static final String SQL="";

    private SparkSession session;
    public DownloadCSV(SparkSession session){
        this.session =session;
    }

    public void execute() throws Exception{
        try {
            Dataset<Row> data = session.sql(EN_SQL);
            toCSV(data,"data.csv");
            };
            for(String s:lang){
                Dataset<Row> langData = session.sql(String.format(LANG_SQL,s));
                toCSV(langData, s+".csv");
            }
        }catch (Exception e){
            throw new Exception("exception occur: ", e);
        }
    }

    private void toCSV(Dataset<Row> data, String filename) throws Exception{
    
        CsvWriterSettings settings = new CsvWriterSettings();
        settings.setHeaders(
                data.columns()
        );
        settings.setHeaderWritingEnabled(true);
        CsvWriter csvWriter = new CsvWriter(
                new File(filename),
                Charset.forName("UTF-8"),
                settings
        );
        for (Row row: data.collectAsList()){
            csvWriter.writeRow(row.mkString("\t"));
        }
        
        csvWriter.flush();
        csvWriter.close();
    }


}
