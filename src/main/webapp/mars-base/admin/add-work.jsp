<%@page import="uz.me.marsbase.controller.command.CommandType" %>
<%@ page import="uz.me.marsbase.controller.command.navigation.AttributeParameterHolder" %>
<%@ page import="uz.me.marsbase.model.entity.enums.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>ADD WORK</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
                        <c:if test="${sessionScope.invalid_form.title!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.title}
                            </div>
                        </c:if>
                    </ol>

                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.description!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.description}
                            </div>
                        </c:if>
                    </ol>
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.requiredMoney!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.requiredMoney}
                            </div>
                        </c:if>
                    </ol>
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.date!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.date}
                            </div>
                        </c:if>
                    </ol>
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.teamName!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.teamName}
                            </div>
                        </c:if>
                    </ol>
                    <ol class="alert-danger">
                        <c:if test="${sessionScope.invalid_form.blockName!=null}">
                            <div class="text-danger">
                                    ${sessionScope.invalid_form.blockName}
                            </div>
                        </c:if>
                    </ol>
                    <br>
                    <br>

                </div>
            </div>
        </div>

        <div class="registerDiv" id="registerDiv" align="center">
            <br>
            <form action="${pageContext.request.contextPath}/controller?command=${CommandType.FINISH_ADD_WORK}"
                  id="register_form"
                  align="center"
                  class="signup-content" method="post">
                <h1 class="signup-title"> Add Work </h1>

                <div class="form-item">
                    <label for="title"></label>
                    <input type="text" class="form-control" id="title" required ="required"
                           name="${AttributeParameterHolder.PARAMETER_WORK_TITLE}"
                           placeholder=" Title ">
                </div>

                <div class="form-item">
                    <label for="description"></label>
                    <input type="text" class="form-control" id="description" required ="required"
                           name="${AttributeParameterHolder.PARAMETER_WORK_DESCRIPTION}"
                           placeholder=" description ">
                </div>

                <div class="form-item">
                    <label for="requiredMoney"></label>
                    <input type="text" class="form-control" id="requiredMoney" required ="required"
                           name="${AttributeParameterHolder.PARAMETER_WORK_REQUIRED_MONEY}"
                           placeholder=" required money ">
                </div>

                <div class="form-item">
                    <label for="startDate"></label>
                    <input type="date" class="form-control" id="startDate" required ="required"
                           name="${AttributeParameterHolder.PARAMETER_WORK_START_DATE}"
                           placeholder=" start date ">
                </div>

                <div class="form-item">
                    <label for="finishDate"></label>
                    <input type="date" class="form-control" id="finishDate" required="required"
                           name="${AttributeParameterHolder.PARAMETER_WORK_FINISH_DATE}"
                           placeholder=" finish date ">
                </div>

                <select name="teamName" class="form-select" size="1" required>
                    <option value="">select team</option>
                    <c:forEach items="${sessionScope.teams}" var="team">
                        <option name="${AttributeParameterHolder.PARAMETER_TEAM_NAME}"
                                value="${team.name}"> ${team.name}</option>
                    </c:forEach>
                </select>

                <select name="blockName" class="form-select" size="1" required>
                    <option value="">select block</option>

                    <c:forEach items="${sessionScope.blocks}" var="block">
                        <option name="${AttributeParameterHolder.PARAMETER_BLOCK_NAME}"
                                value="${block.name}"> ${block.name}</option>
                    </c:forEach>
                </select>


                <div class="form-item">
                    <button type="submit" class="btn btn-block btn-primary">Add Work</button>
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
