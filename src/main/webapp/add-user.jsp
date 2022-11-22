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
    <title>H+ Sport</title>
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<header id="home" class="header">
    <nav class="nav" role="navigation">
        <div class="container nav-elements">
            <!-- branding -->
            <ul class="navbar">
                <li><a href="login">SIGN IN</a></li>

            </ul>
            <!-- navbar -->
        </div>
        <!-- container nav-elements -->
    </nav>

</header>



<section id="registration" class="section">
    <div class="container tagline">
        <em>Register User</em><br/>
        <em>{0}</em>
        <form action="addUser" method="post">
            <label>Email</label> <input type="text" placeholder="Email kiriting" name="email"><br/>
            <label>Password</label><input type="password" placeholder="Parol kiriting" name="password"><br/>
            <label>Firstname</label><input type="text" placeholder="enter firstname" name="firstname"><br/>
            <label>Lastname</label><input type="text" placeholder="enter lastname" name="lastname"><br/>
            <label>BirthDate</label><input type="date" placeholder="enter birthdate" name="birthDate"><br/>
            <label>BlockId</label><input type="number" placeholder="enter block id" name="blockId"><br/>
            <input type="submit" value="Submit" id="submit">
        </form>
    </div>
</section>

</body>
</html>
