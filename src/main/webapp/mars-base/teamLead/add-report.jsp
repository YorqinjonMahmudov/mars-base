<%@page import="uz.me.marsbase.controller.command.CommandType" %>
<%@ page import="uz.me.marsbase.controller.command.navigation.AttributeParameterHolder" %>
<%@ page import="uz.me.marsbase.model.entity.enums.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>ADD REPORT</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<c:choose>

    <c:when test="${sessionScope.currentUser.role.equals(Role.TEAM_LEADER) || sessionScope.currentUser.role.equals(Role.ADMIN) }">

        <div class="container">
            <c:if test="${sessionScope.invalid_form!=null}">
                <div> ${sessionScope.invalid_form}</div>
            </c:if>
        </div>
        <div class="registerDiv" id="registerDiv" align="center">
            <form action="${pageContext.request.contextPath}/controller?command=${CommandType.FINISH_ADD_REPORT}&currentWorkId=${sessionScope.currentWork.id}"
                  id="register_form"
                  class="signup-content" method="post">
                <h1 class="signup-title"> Add Report </h1>

                <div class="form-item">
                    <label for="comments"></label>
                    <input type="text" class="form-control" id="comments"
                           required="required"
                           name="${AttributeParameterHolder.PARAMETER_REPORT_COMMENTS}"
                           placeholder=" comments ">
                </div>

                <div class="form-item">
                    <label for="reportedDate"></label>
                    <input type="date" class="form-control" id="reportedDate" required
                           name="${AttributeParameterHolder.PARAMETER_REPORT_DATE}"
                           placeholder="date">
                </div>

                <div class="form-item">
                    <button type="submit" class="btn btn-block btn-primary">Add Report</button>
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
