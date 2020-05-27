<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 25.05.2020
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="jsp/error.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Products and warehouses</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mdb.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <jsp:include page="/jsp/scripts.jsp"/>
</head>
<body>

<div class="title" onclick="document.location.href='/main'" >Products and warehouses</div>

<div class="row d-flex justify-content-center">
    <div class="col-7">
        <div class="block">
            <c:choose>
                <c:when test="${'addProduct'.equals(action) || 'openProduct'.equals(action)}">
                    <jsp:include page="jsp/addProduct.jsp"/>
                </c:when>
                <c:when test="${'addWarehouse'.equals(action) || 'openWarehouse'.equals(action)}">
                    <jsp:include page="jsp/addWarehouse.jsp"/>
                </c:when>
                <c:when test="${'addRemnant'.equals(action)}">
                    <jsp:include page="jsp/addRemnant.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="jsp/main.jsp"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>

