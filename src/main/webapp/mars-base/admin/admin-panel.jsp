<%@ page import="uz.me.marsbase.constants.AppConstants" %>
<%@ page import="uz.me.marsbase.model.entity.User" %>
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
        body {
            font-family: "Lato", sans-serif;
        }

        .sidenav {
            height: 100%;
            width: 0;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #111;
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 60px;
        }

        .sidenav a {
            padding: 8px 8px 8px 32px;
            text-decoration: none;
            font-size: 25px;
            color: #818181;
            display: block;
            transition: 0.3s;
        }

        .sidenav a:hover {
            color: #f1f1f1;
        }

        .sidenav .closebtn {
            position: absolute;
            top: 0;
            right: 25px;
            font-size: 36px;
            margin-left: 50px;
        }

        @media screen and (max-height: 450px) {
            .sidenav {
                padding-top: 15px;
            }

            .sidenav a {
                font-size: 18px;
            }
        }
    </style>
</head>
<body>



<%--<% if (request.)%>--%>
<div id="mySidenav" class="sidenav">
    <a href="#" onclick="closeNav()"> <span onclick='closeNav()'>&times;</span> </a>
<%--    <form  action="${pageContext.request.contextPath}/controller?command=${CommandType.FINISH_SIGN_IN}" method="post">--%>
<%--        <button type="submit">User</button>--%>
<%--    </form>--%>
<a href="${pageContext.request.contextPath}/controller?command=${CommandType.USERS_FOR_ADMIN}">User</a>
    <a href="../team-info.jsp">Team</a>
    <a href="../work-info.jsp">Work</a>
    <a href="../report-info.jsp">Report</a>
</div>

<h2>Admin panel </h2>
<p>Click on the element below to open the side navigation menu.</p>
<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; MENU</span>

<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
</script>

</body>
</html>
