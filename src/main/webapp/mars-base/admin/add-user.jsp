<%@page import="uz.me.marsbase.command.CommandType" %>
<%@ page import="uz.me.marsbase.command.navigation.AttributeParameterHolder" %>
<%@ page import="uz.me.marsbase.model.entity.enums.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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

        form {
            margin-top: 50px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        input {
            width: 300px;
            padding: 10px 20px 5px 20px;
            font-size: 14px;
            outline: none;
        }

        .form-item input:focus {
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

    <c:when test="${sessionScope.currentUser.role.equals(Role.ADMIN) && sessionScope.teamId==null}">
        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <br>
                    <br>
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.firstName!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.firstName}
                            </div>
                        </c:if>
                    </ol>

                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.lastName!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.lastName}
                            </div>
                        </c:if>
                    </ol>
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.email!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.email}
                            </div>
                        </c:if>
                    </ol>
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.role!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.role}
                            </div>
                        </c:if>
                    </ol>
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.password!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.password}
                            </div>
                        </c:if>
                    </ol>
                    <br>
                    <br>

                </div>
            </div>
        </div>

        <div class="registerDiv card" id="registerDiv">

            <form action="${pageContext.request.contextPath}/controller?command=${CommandType.ADD_USER_FINISH}"
                  id="register_form" align="center"
                  class="signup-content" method="post">
                <h1 class="signup-title"> Add User </h1>

                <div class="form-item">
                    <label for="firstName"></label>
                    <input type="text" class="form-control" id="firstName"
                           name="${AttributeParameterHolder.PARAMETER_USER_FIRSTNAME}"
                           placeholder=" First name ">
                </div>

                <div class="form-item">
                    <label for="lastName"></label>
                    <input type="text" class="form-control" id="lastName"
                           name="${AttributeParameterHolder.PARAMETER_USER_LASTNAME}"
                           placeholder=" Last name ">
                </div>

                <div class="form-item">
                    <label for="email"></label>
                    <input type="text" class="form-control" id="email"
                           name="${AttributeParameterHolder.PARAMETER_USER_EMAIL}"
                           placeholder="Email">
                </div>

                <div class="form-item">
                    <label for="password"></label>
                    <input type="password" class="form-control" id="password"
                           name="${AttributeParameterHolder.PARAMETER_USER_PASSWORD}"
                           placeholder=" Password ">
                </div>

                <select name="blockId" class="form-select" size="1" aria-label="size 3 select example">
                    <option value="">select block</option>

                    <c:forEach items="${sessionScope.blocks}" var="block">
                        <option name="blockId" value="${block.id}"> ${block.name}</option>
                    </c:forEach>

                </select>

                <div class="form-item">
                    <button type="submit" class="btn btn-block btn-primary">Add User</button>
                </div>
            </form>
        </div>
    </c:when>
    <c:when test="${sessionScope.teamId!=null && sessionScope.currentUser.role.equals(Role.TEAM_LEADER)
    ||sessionScope.currentUser.role.equals(Role.ADMIN)}">

        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.email!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.email}
                            </div>
                        </c:if>
                    </ol>
                    <br>
                    <br>

                </div>
            </div>
        </div>

        <div class="registerDiv card" id="registerDiv">
            <h1 class="signup-title"> Add User </h1>

            <form action="${pageContext.request.contextPath}/controller?command=${CommandType.ADD_USER_TO_TEAM}"
                  id="add_user_to_team_form"
                  class="signup-content" method="post">


                <select name="email" class="form-select" size="1" aria-label="size 3 select example" required>
                    <option selected> select user</option>

                    <c:forEach items="${sessionScope.users}" var="user">
                        <option name="email" value="${user.email}"> ${user.email}</option>
                    </c:forEach>
                </select>

                <div class="form-item">
                    <button type="submit" class="btn btn-block btn-primary">Add User</button>
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
