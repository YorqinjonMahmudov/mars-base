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
    <title>Work info</title>
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

        /*body {*/
        /*    background: url("../../images/mars-user.jpg");*/
        /*    background-size: cover;*/
        /*}*/

        table {
            margin: 50px 0px 0px 100px;
            width: 80%;
            backdrop-filter: blur(20px);
        }

        table, tr, th, td {
            padding: 10px 10px;
            text-align: center;
            color: black;
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

<div id="mySidenav" class="sidenav">
    <a href="#" onclick="closeNav()"> <span onclick='closeNav()'>&times;</span> </a>
    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.USERS_FOR_ADMIN}">User</a>
    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.TEAMS_FOR_ADMIN}">Team</a>
    <a href="../work-info.jsp">Work</a>
    <a href="../report-info.jsp">Report</a>
</div>

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

    <div class="registerDiv" id="registerDiv">

        <h1 class="signup-title"> Edit Team </h1>

        <form id="register_form" align="center"
              action="${pageContext.request.contextPath}/controller?command=${CommandType.FINISH_EDIT_TEAM}&editingTeamId=${sessionScope.editingTeam.id}"
              class="add-request-content" method="post">

            <div class="form-item">
                <label for="editingTeamFirstName"></label>
                <input type="text" class="form-control"
                       id="editingTeamFirstName" name="${AttributeParameterHolder.PARAMETER_TEAM_NAME}"
                       property="${sessionScope.editingTeam.name}"
                       value="${sessionScope.editingTeam.name}"
                       placeholder=" Team name ">
            </div>

            <div>
                <select name="teamLeadEmail" class="form-select" size="1"
                        aria-label="size 3 select example">
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
                <a href="${pageContext.request.contextPath}/controller?command=${CommandType.TEAMS_FOR_ADMIN}"
                   class="btn btn-block btn-danger">Cancel</a>
            </div>

        </form>
    </div>

</c:if>


<c:choose>
    <c:when test="${sessionScope.currentUser.role.equals(Role.ADMIN)}">

        <table>
            <tr>
                <th>title</th>
                <th>team name</th>
                <th>block name</th>
            </tr>

            <c:forEach items="${sessionScope.workViews}" var="currentWork">
                <tr class="trHover">
                    <td class="column-1"><span><a
                            href="${pageContext.request.contextPath}/controller?command=${CommandType.WORK_INFO_WITH_REPORT}&currentWorkId=${currentWork.id}"> ${currentWork.title}</a> </span>
                    </td>
                    <td class="column-1">${currentWork.teamName} </td>
                    <td class="column-1">${currentWork.blockName} </td>
                </tr>

            </c:forEach>

        </table>
        <br>

        <form align="center" method="post"
              action="${pageContext.request.contextPath}/controller?command=${CommandType.ADD_WORK}">
            <button>
                ADD WORK
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
