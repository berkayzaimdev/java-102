package TurizmAcenteSistemi.Helper;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnector
{
    private Connection connect = null;

    public Connection connectDB()
    {
        try
        {
            connect=DriverManager.getConnection(Config.DB_URL,Config.DB_USERNAME,Config.DB_PASSWORD);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return connect;
    }

    public static Connection getInstance()
    {
        DBConnector db = new DBConnector();
        return db.connectDB();
    }
}
