import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnection {

    static Connection con;

    static {

        try {
            InputStream is = DbConnection.class.getResourceAsStream("db.properties");
            Properties p = new Properties();
            p.load(is);

            String driverUrl=p.getProperty("driverUrl");
            String jdbcUrl=p.getProperty("jdbcUrl");
            String user=p.getProperty("user");
            String pass=p.getProperty("password");
            Class.forName(driverUrl);
            con=DriverManager.getConnection(jdbcUrl,user,pass);

            System.out.println("connection created bhai");

            /*
                InputStream ip=DbConnection.class.getResourceAsStream("db.properties");
            Properties p=new Properties();
            p.load(ip);
            String driverURL=p.getProperty("driverURL");
            String jdbcURL=p.getProperty("jdbcURL");
            String user=p.getProperty("username");
            String password=p.getProperty("password");
            Class.forName(driverURL);
            con= DriverManager.getConnection(jdbcURL,user,password);
            System.out.println("connection created");
            */
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    static Connection createConnection(){
        return  con;
    }
}
