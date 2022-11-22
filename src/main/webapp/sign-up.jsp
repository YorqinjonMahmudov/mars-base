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
            <div class="branding">
                <a href="#home"><img src="images/hpluslogo.svg"
                                     alt="Logo - H Plus Sports"></a>
            </div>
            <!-- branding -->
            <ul class="navbar">
                <li><a href="#home">home</a></li>
                <li><a href="login">SIGN IN</a></li>

            </ul>
            <!-- navbar -->
        </div>
        <!-- container nav-elements -->
    </nav>

    <!-- <div class="container tagline">
<h1 class="headline">Our Mission</h1>
<p>We support and encourage <em>active and healthy</em> lifestyles, by offering <em>ethically sourced</em> and <em>eco-friendly</em> nutritional products for the <em>performance-driven</em> athlete.</p>
</div>container tagline -->
</header>



<section id="registration" class="section">
    <div class="container tagline">
        <em>Register User</em><br/>
        <em>{0}</em>
        <form action="signUp" method="post">
            <label>Email</label> <input type="text" placeholder="Email kiriting" name="email"><br/>
            <label>Password</label><input type="password" placeholder="Parol kiriting" name="password"><br/>
            <label>Pre-Password</label><input type="password" placeholder="Parol kiriting" name="password"><br/>
            <input type="submit" value="Submit" id="submit">
        </form>
    </div>
</section>

</body>
</html>
