<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11/28/2022
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="uz.me.marsbase.command.CommandType" %>
<%@page import="uz.me.marsbase.model.entity.enums.Role" %>
<%@page import="uz.me.marsbase.command.navigation.AttributeParameterHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team info</title>
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
            background: url("../../images/mars-user.jpg");
            background-size: cover;
        }

        table {
            margin: 50px 0 0 100px;
            width: 80%;
            backdrop-filter: blur(20px);
        }

        table, tr, th, td {
            padding: 10px 10px;
            text-align: center;
            color: #fff;
        }

        table a {
            color: #fff;
            text-decoration: none;
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

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: sans-serif;
        }

        .modal {
            position: absolute;
            z-index: 1000;
            width: 500px;
            height: 500px;
            background: #fff;
            border: 1px solid #000;
            top: 100px;
            left: 500px;
            border-radius: 10px;
            display: flex;
            justify-content: center;
            padding: 30px 0 0 0;
            box-shadow: 0px 0px 20px #000;
        }

        .registerDiv {
            width: 100%;
            text-align: center;
        }

        form {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            margin-top: 50px;
            width: 100%;
        }

        .form-item {
            width: 70%;
            margin: 5px;
        }

        .form-item input {
            width: 100%;
            padding: 10px 20px;
        }

        .select {
            width: 70%;
        }

        .select select {
            width: 100%;
            padding: 10px 20px;
            outline: none;
        }

        .buttons {
            display: flex;
            margin-top: 10px;
        }

        .buttons button {
            padding: 10px 25px;
            background: #ff0;
            margin-left: 50px;;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .buttons .cancel {
            padding: 6px 20px;
            border-radius: 5px;
            background: red;
            color: #fff;
        }

        .buttons .cancel a {
            color: #fff;
            text-decoration: none;
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

<div id="mySidenav" class="sidenav">
    <a href="#" onclick="closeNav()"> <span onclick='closeNav()'>&times;</span> </a>
    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.USERS_FOR_ADMIN}">User</a>
    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.TEAMS}">Team</a>
    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.WORK_PAGE_FOR_ADMIN}">Work</a>
</div>

<span style="font-size:30px;cursor:pointer; color: #fff" onclick="openNav()">&#9776; MENU</span>


<c:if test="${sessionScope.currentUser.role.equals(Role.ADMIN) && sessionScope.editingTeam!=null}">

    <div class="container">
        <div class="row">
            <div class="col text-center">
                <br>
                <br>
                <ol class="alert-danger">
                    <c:if test="${sessionScope.invalid_form.email!=null}">
                        <div class="text-danger">
                                ${sessionScope.invalid_form.email}
                        </div>
                    </c:if>
                </ol>

                <ol class="alert-danger">
                    <c:if test="${sessionScope.invalid_form.password!=null}">
                        <div class="text-danger">
                                ${invalid_form.password}
                        </div>
                    </c:if>
                </ol>

                <ol class="alert-danger">
                    <c:if test="${sessionScope.invalid_form.lastname!=null}">
                        <div class="text-danger">
                                ${sessionScope.invalid_form.lastname}
                        </div>
                    </c:if>
                </ol>

                <ol class="alert-danger">
                    <c:if test="${sessionScope.invalid_form.firstname!=null}">
                        <div class="text-danger">
                                ${sessionScope.invalid_form.firstname}
                        </div>
                    </c:if>
                </ol>

                <br>
                <br>

            </div>
        </div>
    </div>

    <div class="modal">
        <div class="registerDiv" id="registerDiv">

            <h1 class="edit-title"> Edit Team </h1>

            <form id="register_form" align="center"
                  action="${pageContext.request.contextPath}/controller?command=${CommandType.FINISH_EDIT_TEAM}&editingTeamId=${sessionScope.editingTeam.id}"
                  class="add-request-content" method="post">

                <div class="form-item">
                    <label for="editingTeamName"> </label>
                    <input type="text" class="form-control"
                           id="editingTeamName" name="${AttributeParameterHolder.PARAMETER_TEAM_NAME}"
                           property="${sessionScope.editingTeam.name}"
                           value="${sessionScope.editingTeam.name}"
                           placeholder=" Team name ">
                </div>

                <div class="select">
                    <select name="teamLeadEmail" class="form-select" size="1">
                        <option selected>${sessionScope.editingTeam.teamLeadEmail}</option>

                        <c:forEach items="${sessionScope.users}" var="user">
                            <option name="teamLeadEmail" value="${user.email}"> ${user.email}</option>
                        </c:forEach>
                    </select>
                </div>


                <div class="form-item">
                    <button type="submit" class="btn btn-block btn-primary">Edit</button>
                </div>

                <div class="form-item">
                    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.TEAMS}"
                       class="btn btn-block btn-danger">Cancel</a>
                </div>

            </form>
        </div>
    </div>

</c:if>


<c:choose>
    <c:when test="${sessionScope.currentUser.role.equals(Role.ADMIN)}">

        <table>
            <tr>
                <th>team name</th>
                <th>team leader email</th>
                <th>active</th>
                <th colspan="2">action</th>
            </tr>

            <c:forEach items="${sessionScope.teams}" var="team">
                <tr class="trHover">

                    <td class="column-1"><span><a
                            href="${pageContext.request.contextPath}/controller?command=${CommandType.TEAM_MEMBERS_ADMIN}&teamId=${team.id}"> ${team.name}</a> </span>
                    </td>
                    <td class="column-1"> ${team.teamLeadEmail}
                    </td>
                    <td class="column-1"> ${team.active}
                    </td>
                    <td class="column-row">
                        <a class="btn btn-outline-primary"
                           href="${pageContext.request.contextPath}/controller?command=${CommandType.EDIT_TEAM}&editingTeamId=${team.id}"
                           style="color: yellow">
                            EDIT</a>
                    </td>
                    <td class="column-row">
                        <a href=${pageContext.request.contextPath}/controller?command=${CommandType.DELETE_TEAM}&deletingTeamId=${team.id}
                           style="color: red">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
        <br>

        <form align="center" method="post"
              action="${pageContext.request.contextPath}/controller?command=${CommandType.ADD_TEAM}">
            <button>
                ADD TEAM
            </button>
        </form>
    </c:when>
    <c:otherwise>
        You have no permission to this page
    </c:otherwise>
</c:choose>

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
