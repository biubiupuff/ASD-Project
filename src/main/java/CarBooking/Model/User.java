package CarBooking.Model;

import java.time.LocalDate;

public class User {
    private int ID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private String phoneNumber;

    public User(int ID, String email, String password, String firstName, String lastName, LocalDate DOB, String phoneNumber) {
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
    }

    public User(String email, String password, String firstName, String lastName, LocalDate DOB, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDOB() {
        return DOB;
    }
    public String getDOBAsString() {
        return DOB.toString();
    }
    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
