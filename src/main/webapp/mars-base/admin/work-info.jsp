<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11/28/2022
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="uz.me.marsbase.controller.command.CommandType" %>
<%@page import="uz.me.marsbase.model.entity.enums.Role" %>
<%@page import="uz.me.marsbase.model.entity.enums.Status" %>
<%@page import="uz.me.marsbase.controller.command.navigation.AttributeParameterHolder" %>
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

        body {
            background: url("../../static/images/mars-user.jpg");
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

        table, tr, th, td a {
            color: whitesmoke;
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
            height: 600px;
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
            margin: 4px;
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
    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.LOG_OUT}">Log out</a>

</div>

<span style="font-size:30px;cursor:pointer; color: #fff" onclick="openNav()">&#9776; MENU</span>


<c:if test="${sessionScope.currentUser.role.equals(Role.ADMIN) && sessionScope.editingWork!=null}">

    <div class="container">
        <div class="row">
            <div class="col text-center">
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
        <div class="registerDiv" id="registerDivWork">

            <h1 class="title"> Edit Work </h1>

            <form id="register_form_work" align="center"
                  action="${pageContext.request.contextPath}/controller?command=${CommandType.FINISH_EDIT_WORK}&editingWorkId=${sessionScope.editingWork.id}"
                  class="add-request-content" method="post">

                <div class="form-item">
                    <label for="title"></label>
                    <input type="text" class="form-control" required="required"
                           id="title" name="${AttributeParameterHolder.PARAMETER_WORK_TITLE}"
                           property="${sessionScope.editingWork.title}"
                           value="${sessionScope.editingWork.title}"
                           placeholder=" title ">
                </div>

                <div class="form-item">
                    <label for="description"></label>
                    <input type="text" class="form-control" required="required"
                           id="description" name="${AttributeParameterHolder.PARAMETER_WORK_DESCRIPTION}"
                           property="${sessionScope.editingWork.description}"
                           value="${sessionScope.editingWork.description}"
                           placeholder=" description ">
                </div>

                <div class="form-item">
                    <label for="requiredMoney"></label>
                    <input type="number" class="form-control" required="required"
                           id="requiredMoney" name="${AttributeParameterHolder.PARAMETER_WORK_REQUIRED_MONEY}"
                           property="${sessionScope.editingWork.requiredMoney}"
                           value="${sessionScope.editingWork.requiredMoney}"
                           placeholder=" required money ">
                </div>

                <div class="select">
                    <select name="star" class="form-select" size="1" required>
                        <option selected>${sessionScope.editingWork.star}</option>
                        <option name="star" value="0">0</option>
                        <option name="star" value="1">1</option>
                        <option name="star" value="2">2</option>
                        <option name="star" value="3">3</option>
                        <option name="star" value="4">4</option>
                        <option name="star" value="5">5</option>
                        <option name="star" value="6">6</option>
                        <option name="star" value="7">7</option>
                        <option name="star" value="8">8</option>
                        <option name="star" value="9">9</option>
                        <option name="star" value="10">10</option>
                    </select>
                </div>

                <div class="select">
                    <select name="status" class="form-select" size="1" required="required">
                        <option selected>${sessionScope.editingWork.status}</option>

                        <c:forEach items="${Status.values()}" var="status">
<%--                            <c:if test="${status!=Status.REPORTED && sessionScope.editingWork.status.Equals(Status.NEW)}">--%>
                                <option name="status" value="${status}"> ${status}</option>
<%--                            </c:if>--%>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-item">
                    <label for="startDate"></label>
                    <input type="date" class="form-control" required="required"
                           id="startDate" name="${AttributeParameterHolder.PARAMETER_WORK_START_DATE}"
                           property="${sessionScope.editingWork.startDate}"
                           value="${sessionScope.editingWork.startDate}"
                           placeholder=" start date ">
                </div>

                <div class="form-item">
                    <label for="finishDate"></label>
                    <input type="date" class="form-control" required="required"
                           id="finishDate" name="${AttributeParameterHolder.PARAMETER_WORK_FINISH_DATE}"
                           property="${sessionScope.editingWork.finishDate}"
                           value="${sessionScope.editingWork.finishDate}"
                           placeholder=" finish date ">
                </div>

                <div class="select">
                    <select name="teamName" class="form-select" size="1" required="required">
                        <option selected>${sessionScope.editingWork.teamName}</option>

                        <c:forEach items="${sessionScope.teams}" var="team">
                            <option name="teamName" value="${team.name}"> ${team.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="select">
                    <select name="blockName" class="form-select" size="1" required="required">
                        <option selected>${sessionScope.editingWork.blockName}</option>

                        <c:forEach items="${sessionScope.blocks}" var="block">
                            <option name="blockName" value="${block.name}"> ${block.name}</option>
                        </c:forEach>
                    </select>
                </div>


                <div class="buttons">
                    <div class="cancel">
                        <a href="${pageContext.request.contextPath}/controller?command=${CommandType.WORK_INFO_WITH_REPORT}&currentWorkId=${sessionScope.currentWork.id}"
                           class="btn btn-block btn-danger">Cancel</a>
                    </div>

                    <div class="edit">
                        <button type="submit" class="btn btn-block btn-primary">Edit</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</c:if>


<c:choose>
    <c:when test="${sessionScope.currentUser.role.equals(Role.ADMIN) ||sessionScope.currentUser.role.equals(Role.TEAM_LEADER)}">

        <h1 align="center" style="color: #ffffff">Work details</h1>
        <table>
            <tr>
                <th>title</th>
                <th>description</th>
                <th>required money</th>
                <th>status</th>
                <th>start date</th>
                <th>finish date</th>
                <th>star</th>
                <th>team name</th>
                <th>block name</th>
            </tr>

            <tr class="trHover">
                <td class="column-1"> ${sessionScope.currentWork.title} </td>
                <td class="column-1"> ${sessionScope.currentWork.description} </td>
                <td class="column-1"> ${sessionScope.currentWork.requiredMoney} </td>
                <td class="column-1"> ${sessionScope.currentWork.status} </td>
                <td class="column-1"> ${sessionScope.currentWork.startDate} </td>
                <td class="column-1"> ${sessionScope.currentWork.finishDate} </td>
                <td class="column-1"> ${sessionScope.currentWork.star} </td>
                <td class="column-1"> ${sessionScope.currentWork.teamName} </td>
                <td class="column-1"> ${sessionScope.currentWork.blockName} </td>

                <c:if test="${sessionScope.currentUser.role.equals(Role.ADMIN)}">

                    <td class="column-1"><span> <a
                            href="${pageContext.request.contextPath}/controller?command=${CommandType.EDIT_WORK}&editingWorkId=${sessionScope.currentWork.id}"> EDIT</a> </span>
                    </td>
                    <td class="column-1"><span>  <a
                            href="${pageContext.request.contextPath}/controller?command=${CommandType.DELETE_WORK}&deletingWorkId=${sessionScope.currentWork.id}"> DELETE</a> </span>
                    </td>
                </c:if>

            </tr>

        </table>

        <c:if test="${sessionScope.currentWorkReport==null &&sessionScope.currentWork.status.equals(Status.NEW)  && (sessionScope.currentUser.role.equals(Role.TEAM_LEADER) || sessionScope.currentUser.role.equals(Role.ADMIN))}">
            <div>
                <a style="color: whitesmoke" align="center"
                   href="${pageContext.request.contextPath}/controller?command=${CommandType.ADD_REPORT}&currentWorkId=${sessionScope.currentWork.id}">
                    Add Report</a>
            </div>
        </c:if>


        <c:if test="${sessionScope.currentWorkReport!=null && (sessionScope.currentUser.role.equals(Role.TEAM_LEADER) || sessionScope.currentUser.role.equals(Role.ADMIN))}">

            <c:if test="${sessionScope.editingReport!=null}">

                <div class="modal">
                    <div class="registerDiv" id="registerDivReport">

                        <h1 class="title"> Edit Report </h1>

                        <div class="container">
                            <c:if test="${sessionScope.invalid_form!=null}">
                                <div style="color: red"> ${sessionScope.invalid_form}</div>
                            </c:if>
                        </div>

                        <form id="register_form_report" align="center"
                              action="${pageContext.request.contextPath}/controller?command=${CommandType.FINISH_EDIT_REPORT}&editingWorkId=${sessionScope.currentWork.id}&editingReportId=${sessionScope.editingReport.id}"
                              class="add-request-content" method="post">

                            <div class="form-item">
                                <label for="comments"></label>
                                <input type="text" class="form-control" id="comments"
                                       required="required"
                                       property="${sessionScope.editingReport.comments}"
                                       value="${sessionScope.editingReport.comments}"
                                       name="${AttributeParameterHolder.PARAMETER_REPORT_COMMENTS}"
                                       placeholder=" comments ">
                            </div>

                            <div class="form-item">
                                <label for="reportDate"></label>
                                <input type="date" class="form-control" id="reportDate"
                                       property="${sessionScope.editingReport.reportedDate}"
                                       value="${sessionScope.editingReport.reportedDate}"
                                       name="${AttributeParameterHolder.PARAMETER_REPORT_DATE}"
                                       placeholder="date">
                            </div>


                            <div class="buttons">
                                <div class="cancel">
                                    <a href="${pageContext.request.contextPath}/controller?command=${CommandType.WORK_INFO_WITH_REPORT}&currentWorkId=${sessionScope.currentWork.id}"
                                       class="btn btn-block btn-danger">Cancel</a>
                                </div>

                                <div class="edit">
                                    <button type="submit" class="btn btn-block btn-primary">Edit</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>

            </c:if>


            <table>
                <tr>
                    <th>work id</th>
                    <th>reported date</th>
                    <th>comments</th>
                    <c:if test="${sessionScope.currentWork.status.equals(Status.REPORTED)}">
                        <th colspan="1">action</th>
                    </c:if>
                </tr>

                <tr class="trHover">
                    <td class="column-1"> ${sessionScope.currentWorkReport.workId} </td>
                    <td class="column-1"> ${sessionScope.currentWorkReport.reportedDate} </td>
                    <td class="column-1"> ${sessionScope.currentWorkReport.comments} </td>
                    <c:if test="${sessionScope.currentWork.status.equals(Status.REPORTED)}">
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=${CommandType.EDIT_REPORT}&currentWorkReportId=${sessionScope.currentWorkReport.id}">
                                EDIT</a></td>
                    </c:if>

                </tr>


            </table>

        </c:if>


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
