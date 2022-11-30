<%@ page import="java.util.List" %>
<%@ page import="uz.me.marsbase.entity.User" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11/28/2022
  Time: 9:55 AM
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
    <a href="get-all-users">User</a>
    <a href="team-info.jsp">Team</a>
    <a href="work-info.jsp">Work</a>
    <a href="report-info.jsp">Report</a>
</div>

<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; MENU</span>

<table border="1">
    <tr>
        <th>first name</th>
        <th>last name</th>
        <th>email</th>
        <th>password</th>
        <th>role</th>
        <th>block id</th>
        <th colspan="2">action</th>
    </tr>


    <% List<User> users = (List<User>) request.getAttribute("users");
        for (User user : users) {
    %>
    <tr>
        <td><%= user.getFirstName()%>
        </td>
        <td><%= user.getLastName()%>
        </td>
        <td><%= user.getEmail()%>
        </td>
        <td><%= user.getPassword()%>
        </td>
        <td><%= user.getRole()%>
        </td>
        <td><%= user.getBlockId()%>
        </td>
        <td><a methods="put" href="edit-user/<%=user.getId()%>"> edit</a></td>
        <td><a href="delete-user/${user.getId()}"> delete</a></td>
    </tr>
    <% } %>
</table>

<form method="post" action="add-user.jsp">
    <button>
        ADD USER
    </button>


</form>

<%--<a href="addUser"> Add user </a>--%>
<%--<a href="get-all"> list of users </a>--%>
<%--<a onclick=""> user </a>--%>
<%--<a href="addUser"> Add user </a>--%>


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
