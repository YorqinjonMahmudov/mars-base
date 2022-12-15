<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mars base</title>
    <link rel="stylesheet" href="https://fonts.googleapi/s.com/css?family=Raleway">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: sans-serif;
        }

        body {
            background: url("../static/images/mars.png");
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 400px;
            height: 400px;
            display: flex;
            flex-direction: column;
            align-items: center;
            backdrop-filter: blur(10px);
            border-radius: 10px;
        }

        .title {
            margin: 50px 0 30px 0;
        }

        h1 {
            color: rgba(255, 255, 255, 0.75);
            text-align: center;
            font-size: 30px;
        }

        a {
            text-decoration: none;
            font-size: 20px;
            background: #000;
            width: 80%;
            padding: 20px 0;
            color: #fff;
            text-align: center;
            border-radius: 10px;
            margin-top: 50px;
        }
    </style>
</head>

<body>
<div class="container">

    <div class="title">
        <h1>Welcome to Mars base</h1>
        <h1> Please, login to get started</h1>
    </div>
    <a href="../mars-base/sign-in.jsp">Login</a>
</div>
</body>
</html>
