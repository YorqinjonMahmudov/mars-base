<%@page import="uz.me.marsbase.command.CommandType" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11/28/2022
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: sans-serif;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 96vh;
            background: #000;
            overflow: hidden;
        }

        ul {
            text-align: center;
        }

        li {
            position: relative;
            padding: 10px 10px;
            list-style: none;
            transition: 0.5s;
            z-index: 100000;
        }

        a {
            text-decoration: none;
            color: #fff;
            font-size: 22px;
            top: 0;
            left: 0;
            transition: 0.3s;
        }

        li:hover a {
            font-size: 30px;
        }
    </style>
</head>
<body>

<ul>
    <li class="user"><a
            href="${pageContext.request.contextPath}/controller?command=${CommandType.USERS_FOR_ADMIN}">User</a></li>
    <li class="team"><a
            href="${pageContext.request.contextPath}/controller?command=${CommandType.TEAMS}">Team</a></li>
    <li class="work"><a href="${pageContext.request.contextPath}/controller?command=${CommandType.WORK_PAGE_FOR_ADMIN}">Work</a>
    </li>
    <li class="log0ut">
        <a href="${pageContext.request.contextPath}/controller?command=${CommandType.LOG_OUT}">Log out</a>
    </li>
</ul>

</body>
</html>
