package CarBooking.Model.dao;
import CarBooking.Model.User;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    ResultSet rs;
    public UserDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }
    public ArrayList<User> queryUsers() throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM USER");
        rs = prepStmt.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while(rs.next()) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(rs.getString(6), format);
            users.add(new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    dob,
                    rs.getString(7)));
        }
        return users;
    }
    public boolean authenticateUser(String email, String password) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?");
        prepStmt.setString(1, email);
        prepStmt.setString(2, password);
        rs = prepStmt.executeQuery();

        return rs.next() ? true : false;
    }
    public void addUser(User user) throws SQLException {
        prepStmt = conn.prepareStatement("INSERT INTO USER " +
                "(EMAIL, PASSWORD, FIRSTNAME, LASTNAME, DOB, PHONENUMBER)" +
                "VALUES (?, ?, ?, ?, ?, ?)");
        prepStmt.setString(1, user.getEmail());
        prepStmt.setString(2, user.getPassword());
        prepStmt.setString(3, user.getFirstName());
        prepStmt.setString(4, user.getLastName());
        prepStmt.setString(5, user.getDOBAsString());
        prepStmt.setString(6, user.getPhoneNumber());
        prepStmt.executeUpdate();

    }
    public void deleteUser(int ID) throws SQLException {
        prepStmt = conn.prepareStatement("DELETE FROM USER WHERE ID = ?");
        prepStmt.setInt(1, ID);
        prepStmt.executeUpdate();
    }
    public User findUser(String email, String password) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?");
        prepStmt.setString(1, email);
        prepStmt.setString(2, password);
        rs = prepStmt.executeQuery();
        if(rs.next()) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(rs.getString(6), format);
            return new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    dob,
                    rs.getString(7));
        }
        return null;
    }
    public User findUserByID(int ID) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM USER WHERE ID = ?");
        prepStmt.setInt(1, ID);
        rs = prepStmt.executeQuery();
        if(rs.next()) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(rs.getString(6), format);
            return new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    dob,
                    rs.getString(7));
        }
        return null;
    }
    public void updateUser(User updatedUser) throws SQLException {
        prepStmt = conn.prepareStatement("UPDATE USER " +
                "SET EMAIL = ?, PASSWORD = ?, FIRSTNAME = ?, LASTNAME = ?, DOB = ?, PHONENUMBER = ?" +
                "WHERE ID = ?");
        prepStmt.setString(1, updatedUser.getEmail());
        prepStmt.setString(2, updatedUser.getPassword());
        prepStmt.setString(3, updatedUser.getFirstName());
        prepStmt.setString(4, updatedUser.getLastName());
        prepStmt.setString(5, updatedUser.getDOBAsString());
        prepStmt.setString(6, updatedUser.getPhoneNumber());
        prepStmt.setInt(7, updatedUser.getID());
        prepStmt.executeUpdate();
    }
    public void resetUserDB() throws SQLException {
        stmt.executeUpdate("DROP TABLE USER");
        stmt.executeUpdate("CREATE TABLE \"User\" (\n" +
                "\t\"ID\"\tINTEGER NOT NULL UNIQUE,\n" +
                "\t\"Email\"\tTEXT NOT NULL UNIQUE,\n" +
                "\t\"Password\"\tTEXT NOT NULL,\n" +
                "\t\"FirstName\"\tTEXT NOT NULL,\n" +
                "\t\"LastName\"\tTEXT NOT NULL,\n" +
                "\t\"DOB\"\tTEXT NOT NULL,\n" +
                "\t\"PhoneNumber\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"ID\" AUTOINCREMENT)\n" +
                ")");

    }
}
