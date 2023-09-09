<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 400px;
            margin: 100px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            color: #555;
            margin-bottom: 5px;
        }

        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="date"],
        .form-group input[type="email"],
        .form-group input[type="tel"] {
            width: 100%;
            padding: 8px;
            font-size: 16px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .button-container button {
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 3px;
            border: none;
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .button-container button:hover {
            background-color: #45a049;
        }

        .guest-link {
            text-align: center;
            margin-top: 10px;
            color: #666;
        }

        .guest-link a {
            color: #666;
            text-decoration: none;
        }
        a {
            text-decoration: none;
            color: inherit;
        }
        .guest-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%
    //    Validator v = new Validator(session);
    String existErr = (String) session.getAttribute("existErr");
    String emailErr = (String) session.getAttribute("emailErr");
    String passErr = (String) session.getAttribute("passErr");
    String userExists = (String) session.getAttribute("userExists");
    String phoneErr = (String) session.getAttribute("phoneErr");
%>
<form action="RegisterServlet" method="post">
    <div class="container">
        <h1>Registration</h1>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required placeholder="<%=(emailErr != null ? emailErr : "Enter your email")%>">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required placeholder="<%=(passErr != null ? passErr : "Enter your password")%>">
        </div>
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required placeholder="Enter your first name">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required placeholder="Enter your last name">
        </div>
        <div class="form-group">
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob">
        </div>
        <div class="form-group">
            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" required placeholder="<%=(phoneErr != null ? phoneErr : "Enter your phone number") %>">
        </div>
        <span><%=(userExists != null ? userExists : "") %></span>
        <div class="button-container">
            <button><a href="index.jsp">Back</a></button>
            <button type="submit">Register</button>
        </div>
        <div class="guest-link">
            <a href="GuestServlet">Continue as Guest</a>
        </div>
    </div>
</form>
</body>
</html>