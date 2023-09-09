package CarBooking.Model.dao;

import CarBooking.Model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserDBManagerTest {
    TestDBConnector db;
    UserDBManager userDBManager;
    ArrayList<User> usersList = new ArrayList<>();
    User userOne = new User(
            "Tomgolding2000@outlook.com",
            "Password1",
            "Tom",
            "Golding",
            LocalDate.of(1999, 3, 13),
            "0417503531");
    User userTwo = new User(
            "BobBecker2012@outlook.com",
            "Password123",
            "Bob",
            "Becker",
            LocalDate.of(1992, 3, 11),
            "0417503523");
    {
        try {
            db = new TestDBConnector();
            userDBManager = new UserDBManager(db.conn);
            usersList.add(userOne);
            usersList.add(userTwo);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void addUser() {

        try {
            userDBManager.resetUserDB();
            for(User user : usersList) {
                userDBManager.addUser(user);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateUser() {
        try {
            String oldPw = userTwo.getPassword();
            String updatedPw = "MyNewPassword123";
            userTwo.setPassword(updatedPw);
            userTwo.setID(2);
            userDBManager.updateUser(userTwo);
            User updatedUser = userDBManager.findUser(userTwo.getEmail(), userTwo.getPassword());
            assertEquals(updatedUser.getPassword(), updatedPw);
            userTwo.setPassword(oldPw);
            userDBManager.updateUser(userTwo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void queryUsers() {
        try {
            ArrayList<User> queriedUsers = userDBManager.queryUsers();
            for(int i = 0; i < queriedUsers.size(); i++) {
                User queriedUser = queriedUsers.get(i);
                User demoUser = usersList.get(i);
                assertEquals(queriedUser.getID(), i + 1);
                assertEquals(queriedUser.getEmail(), demoUser.getEmail());
                assertEquals(queriedUser.getPassword(), demoUser.getPassword());
                assertEquals(queriedUser.getFirstName(), demoUser.getFirstName());
                assertEquals(queriedUser.getLastName(), demoUser.getLastName());
                assertEquals(queriedUser.getDOBAsString(), demoUser.getDOBAsString());
                assertEquals(queriedUser.getPhoneNumber(), demoUser.getPhoneNumber());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void deleteUser() {
//        try {
//            userDBManager.removeUser(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Test
    void findUser() {
        try {
            User realUser = userDBManager.findUser(userOne.getEmail(), userOne.getPassword());
            assertNotNull(realUser);
            User fakeUser = userDBManager.findUser("KimKardasian1954@Gmail.com", "Kimk123");
            assertNull(fakeUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void findUserByID() {
        try {
            User user = userDBManager.findUserByID(1);
            assertNotNull(user);

            user = userDBManager.findUserByID(2);
            assertNotNull(user);

            user = userDBManager.findUserByID(3);
            assertNull(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void authenticateUser() {
        try {
            assertTrue(userDBManager.authenticateUser(userOne.getEmail(), userOne.getPassword()));
            assertTrue(userDBManager.authenticateUser(userTwo.getEmail(), userTwo.getPassword()));
            assertFalse(userDBManager.authenticateUser("BobbyG@gmail.com", "Bobmeister123"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}