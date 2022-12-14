<%@page import="uz.me.marsbase.command.CommandType" %>
<%@ page import="uz.me.marsbase.command.navigation.AttributeParameterHolder" %>
<%@ page import="uz.me.marsbase.model.entity.enums.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add team</title>
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
        }
        .card {
            width: 350px;
            height: 400px;
            display: flex;
            align-items: center;
            flex-direction: column;
            padding: 30px 0 0 0;
            box-shadow: 5px 1px 20px #000;
            border-radius: 10px;
        }

        form{
            margin-top: 50px;
        }

        input {
            width: 300px;
            padding: 10px 20px 5px 20px;
            font-size: 14px;
            outline: none;
        }
        .form-item input:focus{
            border: 1px solid #000;
        }

        .form-select {
            margin-top: 20px;
            width: 300px;
            padding: 10px 20px;
        }

        button {
            margin-top: 20px;
            margin-left: 100px;
            padding: 10px 20px;
            background: none;
        }
    </style>
</head>
<body>

<c:choose>

    <c:when test="${sessionScope.currentUser.role.equals(Role.ADMIN) }">


        <div class="registerDiv card" id="registerDiv">
            <h1 class="signup-title"> Add Team </h1>

            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <br>
                        <ol class="alert-danger">
                            <c:if test="${sessionScope.invalid_form.teamName!=null}">
                                <div class="text-danger" style="color: red">
                                        ${sessionScope.invalid_form.teamName}
                                </div>
                            </c:if>
                        </ol>

                        <ol class="alert-danger">
                            <c:if test="${sessionScope.invalid_form.teamLeadEmail!=null}">
                                <div class="text-danger" style="color: red">
                                        ${sessionScope.invalid_form.teamLeadEmail}
                                </div>
                            </c:if>
                        </ol>
                        <br>

                    </div>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/controller?command=${CommandType.FINISH_ADD_TEAM}"
                  id="register_form"
                  class="signup-content" method="post">

                <div class="teamName">
                    <input  required="required"  type="text" class="form-control" id="firstName"
                           name="${AttributeParameterHolder.PARAMETER_TEAM_NAME}"
                           placeholder=" Team name ">
                </div>

                <select name="teamLeadEmail" class="form-select" size="1"  required="required">
                    <option value="">select user</option>
                    <c:forEach items="${sessionScope.users}" var="user">
                        <option name="${AttributeParameterHolder.PARAMETER_TEAM_LEAD_EMAIL}"
                                value="${user.email}"> ${user.email}</option>
                    </c:forEach>
                </select>


                <div class="form-item">
                    <button type="submit" class="btn btn-block btn-primary">Add Team</button>
                </div>
            </form>
        </div>
    </c:when>

    <c:otherwise>
        You have no permission to this page
    </c:otherwise>

</c:choose>


</body>
</html>
