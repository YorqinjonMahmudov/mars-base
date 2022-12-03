<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11/22/2022
  Time: 7:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mars Base</title>
    <link rel="stylesheet" href="../css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<section id="registration" class="section">
    <div class="container tagline">
        <em>Register User</em><br/>
<%--        <em>{0}</em>--%>
        <form action="addUser" method="post">
            <label>Email</label> <label>
            <input type="text" placeholder=" Enter Email " name="email">
        </label><br/>
            <label>Password</label><label>
            <input type="password" placeholder="Enter password" name="password">
        </label><br/>
            <label>Firstname</label><label>
            <input type="text" placeholder="enter firstname" name="firstname">
        </label><br/>
            <label>Lastname</label><label>
            <input type="text" placeholder="enter lastname" name="lastname">
        </label><br/>
            <label>BlockId</label><label>
            <input type="number" placeholder="enter block id" name="blockId">
        </label><br/>
            <input type="submit" value="Submit" id="submit">
        </form>
    </div>
</section>

</body>
</html>
