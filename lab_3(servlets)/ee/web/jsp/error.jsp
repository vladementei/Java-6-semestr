<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 16.05.2020
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Error page</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/mdb.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
</head>
<body style="height: 100%; position: relative">
<div class="error-container">
    <a type="button" href="/card-designer" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></a>
    <div class="title">${pageContext.errorData.statusCode}</div>
    <div class="requested-url">Error from URI ${pageContext.errorData.requestURI}</div>
    <div class="error-message"> ${pageContext.errorData.throwable.message}</div>
</div>
</body>
</html>
