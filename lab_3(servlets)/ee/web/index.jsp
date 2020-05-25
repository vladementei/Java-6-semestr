<%@ page import="by.dementei.NavigationPages" %>
<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 28.04.2020
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Card Designer</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mdb.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <jsp:include page="/jsp/scripts.jsp"/>
</head>
  <body>

  <div class="title">Card Designer</div>
  <jsp:include page="jsp/navigation.jsp">
    <jsp:param name="page" value='<%=(request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1)%>'/>
    <jsp:param name="pageNames" value="${NavigationPages.getLabels()}"/>
  </jsp:include>

  <div class="row d-flex justify-content-center">
    <div class="col-5">
      <div class="content d-flex flex-column">
        <div class="d-flex flex-column">
          <jsp:include page='<%="jsp/".concat(request.getParameter("page") != null ? request.getParameter("page") : "1").concat(".jsp")%>'/>
        </div>
      </div>
    </div>
  </div>

  </body>
</html>
