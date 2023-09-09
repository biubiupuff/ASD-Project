package CarBooking.Model.dao;

import java.sql.*;

public abstract class TestDB {
    protected String driver = "org.sqlite.JDBC";
    protected String url = "jdbc:sqlite:/Users/tomgolding/Desktop/ASD/ASD-Project/ASD_DB_TEST.db";

    protected Connection conn;
}