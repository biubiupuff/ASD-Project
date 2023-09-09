<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="CarBooking.Model.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Car Park Booking</title>
</head>
<body>
    <%
        String existErr = (String) session.getAttribute("existErr");
        String emailErr = (String) session.getAttribute("emailErr");
        String passErr = (String) session.getAttribute("passErr");
    %>
    <div class="container">
        <h1>Welcome</h1>

    </div>
    <jsp:include page="/ConnServlet" flush="true" />
</body>
</html>