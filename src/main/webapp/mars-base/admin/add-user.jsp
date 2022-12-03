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

    <c:when test="${sessionScope.current_user.role.equals(Role.ADMIN) }">

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

        <div class="registerDiv" id="registerDiv">

            <br>
            <br>
            <br>

            <form action="${pageContext.request.contextPath}/controller?command=${CommandType.ADD_USER_FINISH}"
                  id="register_form"
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
                    <label for="phoneNumber"></label>
                    <input type="text" class="form-control" id="phoneNumber"
                           name="${AttributeParameterHolder.PARAMETER_USER_EMAIL}"
                           placeholder="Email">
                </div>

                <div class="form-item">
                    <label for="password"></label>
                    <input type="password" class="form-control" id="password"
                           name="${AttributeParameterHolder.PARAMETER_USER_PASSWORD}"
                           placeholder=" Password ">
                </div>

                <select name="blockName"  class="form-select" size="${sessionScope.blocks.size()}" aria-label="size 3 select example">
                    <option selected>Open this select menu</option>

                    <c:forEach items="${sessionScope.blocks}" var="block">
                        <option name="blockName" value="${block.name}"> ${block.name}</option>
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
