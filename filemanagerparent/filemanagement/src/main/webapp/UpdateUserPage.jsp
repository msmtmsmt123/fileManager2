<%@ page import="com.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/5/2018
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateUser</title>
    <link href="Style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div id="container">
    <div id="content">
        <div id="editUser">

            <%
                User user=(User)session.getAttribute("user");
            %>



        <b>My Profile</b> <br>
        <form action="com.controllers.UpdateUserServlet" method="put">
            Name: <input type="text" name="nameEditPage" value="<%=user.getName()%>"> <br>
            Username: <input type="text" name="emailEditPage" value="<%=user.getUsername()%>"> <br>
            Email: <input type="text" name="usernameEditPage" value="<%=user.getEmail()%>"> <br>
            Password <input type="password" name="passEditPage" placeholder="Type new password"> <br>
            <input type="submit" value="Edit Profile">
        </form>

        </div>
    </div>
</div>



</body>
</html>
