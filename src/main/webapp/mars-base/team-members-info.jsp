<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="uz.me.marsbase.model.entity.enums.Role" %>
<%@page import="uz.me.marsbase.command.CommandType" %>
<%@page import="uz.me.marsbase.command.navigation.AttributeParameterHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head title="team members page">
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

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: sans-serif;
        }

        body {
            background: url("../images/mars-user.jpg");
            background-size: cover;
        }

        table {
            margin: 50px 0px 0px 100px;
            width: 80%;
            backdrop-filter: blur(20px);
        }

        table, tr, th, td {
            padding: 10px 10px;
            text-align: center;
            color: #fff;
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

        .open .aaa{
            margin-top: 15px;
        }
        .open a {
            padding: 5px 15px;
            border-radius: 5px;
            cursor: pointer;
        }
        .open a:nth-child(1) {
            background: yellow;
        }
        .open a:nth-child(2) {
            background: red;
            text-decoration: none;
            color: #fff;
        }
        .hide {
            width: 0;
            height: 0;
            overflow: hidden;
        }

        .open {
            position: absolute;
            top: 30vh;
            left: 85vh;
            width: 300px;
            height: 100px;
            background: #fff;
            border-radius: 10px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            z-index: 1000;
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

<div id="mySidenav" class="sidenav" style="color: white">
    <a href="#" onclick="closeNav()"> <span onclick='closeNav()'>&times;</span> </a>
    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.USERS_FOR_ADMIN}">User</a>
    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.TEAMS}">Team</a>
    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.WORK_PAGE_FOR_ADMIN}">Work</a>
</div>

<span style=" color: white; font-size:30px;cursor:pointer " onclick="openNav()">&#9776; MENU</span>

<c:if test="${sessionScope.currentUser.role.equals(Role.ADMIN) || sessionScope.currentUser.role.equals(Role.TEAM_LEADER)}">

    <h1 align="center"> Team Members</h1>

    <table>
        <tr>
            <th>first name</th>
            <th>last name</th>
            <th>email</th>
            <th>role</th>
            <th>block id</th>
            <th colspan="1">action</th>
        </tr>

        <c:forEach items="${sessionScope.users}" var="user">
            <tr class="trHover">
                <td class="column-1"><span> ${user.firstName} </span></td>
                <td class="column-1"><span> ${user.lastName} </span></td>
                <td class="column-1"><span> ${user.email} </span></td>
                <td class="column-1"><span> ${user.role} </span></td>
                <td class="column-1"><span> ${user.blockId} </span></td>

                <td class="column-row">
                    <a id="${user.id}" style="color: red" onclick="openDeleteModal()">DELETE</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <br>

    <form align="center" method="post"
          action="${pageContext.request.contextPath}/controller?command=${CommandType.ADD_USER}">
        <button>
            ADD USER
        </button>

    </form>

    <div class="hide" id="modalBig">
        <h3> Are you sure?</h3>
        <div class="aaa" id="modal">
            <a id="no" onclick="hide()">NO</a>
            <a id="yes" onclick="hide()">YES</a>
        </div>
    </div>

</c:if>


<script>

    function openDeleteModal() {
        let id = event.target.id;
        document.getElementById("modalBig").className = "open"
        let user = document.getElementById("yes");
        user.href = "${pageContext.request.contextPath}/controller?command=${CommandType.DELETE_USER_FROM_TEAM}&deletingUserId=" + id;
    }

    function hide() {
        document.getElementById("modalBig").className = "hide";
    }


    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
</script>

</body>
</html>
