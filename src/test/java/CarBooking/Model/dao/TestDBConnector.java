package CarBooking.Model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TestDBConnector extends TestDB {
    public TestDBConnector() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(url);
    }
    public Connection openConnection() {
        return this.conn;
    }
    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}