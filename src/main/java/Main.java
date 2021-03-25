import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;


import java.time.Year;
import java.util.*;

public class Main {
    private static SparkSession sparkSession;
    private static final String DIRECTORY_TO_READ ="file:///C:/ABC_docker/src/athlete_events.csv";
    //private static final String DIRECTORY_TO_READ ="file:///papka/athlete_events.csv";


    public static final void executeAndSave_1_2(String sql){
        Dataset<Row> sqlResult = sparkSession.sql(sql);
        sqlResult.show(300);
    }
    public static final void executeAndSave_3(){
        Dataset<Row> DiscYears = sparkSession.sql("SELECT DISTINCT Year FROM athlete_events ORDER BY Year DESC");
        List<Row> Years = DiscYears.collectAsList();
        List<Row> Result= new ArrayList<Row>();
        for (Row r : Years) {
            try{
                int year = Integer.parseInt(r.getString(0).split(" ")[0]);
                Result.add(r);
                Result.addAll(sparkSession.sql("SELECT Team, COUNT(Medal) as Medal FROM athlete_events WHERE Medal='Gold' AND Year = '"+r.getString(0).split(" ")[0]+"' GROUP BY Team ORDER BY Medal DESC LIMIT 10").collectAsList());
                System.out.println(Result.toString());
            }catch (Exception t){
                System.out.println("[ что-то пошло не так... ]");
            }
        }
        System.out.println("Ru-ru-ru");
        for (Row r : Result) {
            System.out.println(r.toString()+"\n");
        }
    }

    private static void initSparkSession() {
        sparkSession = SparkSession
                .builder()
                .appName("Java Spark SQL Example")
                .master("local[*]")
                .getOrCreate();
    }
    public static void main(String[] args) {

        initSparkSession();
        Dataset<Row> df = sparkSession
                .read()
                .option("header", true)
                .option("inferSchema", true)
                .option("quotes", "\"")
                .csv(DIRECTORY_TO_READ);
        df.printSchema();
        df.createOrReplaceTempView("athlete_events");
        //executeAndSave_1_2("SELECT Games, COUNT(QWERTY) as Medals From( SELECT CONCAT(Team,'_',Sport,'_',Event) as QWERTY, Games FROM athlete_events WHERE Year LIKE '____' AND Medal != 'NA' GROUP BY QWERTY, Games) GROUP BY Games ORDER BY Games DESC"); // <- первое пункт


        executeAndSave_1_2("SELECT City, COUNT(Medal) as Medals FROM athlete_events WHERE Medal != 'NA' GROUP BY City ORDER BY COUNT(Medal) DESC LIMIT 10"); // <- второе пункт


        //executeAndSave_1_2("SELECT Age, City, Event, Year FROM ( SELECT row_number() OVER(PARTITION BY Year ORDER BY Year DESC, Event DESC) as Age, CONCAT(Team, ' - ' ,Year) as City, COUNT(Medal) as Event, Year from athlete_events where Medal = 'Gold' GROUP BY City ORDER BY Year DESC, Event DESC) as d Where Age < 11");
        //executeAndSave_1_2("SELECT CONCAT(Team, ' - ' ,Year) as TeamYear, COUNT(Medal) as Medals from athlete_events where Medal = 'Gold' GROUP BY TeamYear ORDER BY Medals DESC");
        //executeAndSave_1_2("SELECT Team, Year, Medals FROM ( SELECT Team, Year, COUNT(Medal) as Medals, row_number() OVER(PARTITION BY Year ORDER BY Year DESC, COUNT(Medal) DESC) as Rows from athlete_events where Medal = 'Gold' GROUP BY Team, Year ORDER BY Year DESC, Medals DESC ) WHERE Rows < 11 ");
        //executeAndSave_3();
    }
}