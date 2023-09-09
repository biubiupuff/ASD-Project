package CarBooking.Controller;

import CarBooking.Model.User;
import CarBooking.Model.dao.UserDBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatDob = LocalDate.parse(dob, format);
        String phone = request.getParameter("phone");
        UserDBManager user = (UserDBManager) session.getAttribute("user");
        User newUser = null;
        validator.clear(session);
        if(!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if(!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if(!validator.validatePhone(phone)) {
            session.setAttribute("phoneErr", "Error: Phone number format incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        }
        else {
            try {
                if(user.authenticateUser(email, password)) {
                    newUser = new User(email, password, firstName, lastName, formatDob, phone);
                    user.addUser(newUser);
                    User sessionUser = user.findUser(email, password);
                    session.setAttribute("isLoggedIn", true);
                    if(sessionUser != null) {
                        session.setAttribute("user", sessionUser);
                        request.getRequestDispatcher("MainServlet").include(request, response);
                    }
                } else {
                    session.setAttribute("userExists", "User with email already exists");
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }
            } catch (SQLException | NullPointerException ex) {
                System.out.println(ex.getMessage() == null ? "Customer does not exist" : "Welcome");
            }
        }

    }
}