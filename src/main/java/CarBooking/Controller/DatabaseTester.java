package CarBooking.Controller;

import CarBooking.Model.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseTester {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            // Instantiate what DAO object you want to test
            connector.closeConnection();
            // Call function here you want to test


        } catch(ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(CustomerDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}