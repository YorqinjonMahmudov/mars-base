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
    <link rel="stylesheet" href="../../static/css/main.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/nav_style.css" type="text/css">
</head>
<body>

<c:choose>

    <c:when test="${sessionScope.currentUser.role.equals(Role.ADMIN) }">

        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <br>
                    <br>
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.name!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.name}
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

        <div class="registerDiv" id="registerDiv">
            <br>
            <br>
            <br>
            <form action="${pageContext.request.contextPath}/controller?command=${CommandType.FINISH_ADD_TEAM}"
                  id="register_form"
                  class="signup-content" method="post">
                <h1 class="signup-title"> Add Team </h1>

                <div class="form-item">
                    <label for="firstName"></label>
                    <input type="text" class="form-control" id="firstName"
                           name="${AttributeParameterHolder.PARAMETER_TEAM_NAME}"
                           placeholder=" Team name ">
                </div>

                <select name="teamLeadEmail" class="form-select" size="1"
                        aria-label="size 3 select example">
                    <option selected>Open this select menu</option>

                    <c:forEach items="${sessionScope.users}" var="user">
                        <option name="${AttributeParameterHolder.PARAMETER_TEAM_LEAD_EMAIL}" value="${user.email}"> ${user.email}</option>
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
