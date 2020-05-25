<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 10.05.2020
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="row d-flex justify-content-center">
    <div class="col-8">
        <ul class="stepper stepper-horizontal">
            <c:forEach var="pageName" items="${param.pageNames}" varStatus="counter">
                <li class="${((sessionScope.get("finishedSteps")[counter.count] == true && !param.page.equals("".concat(counter.count))) ? "completed accessible":
                    (param.page.equals("".concat(counter.count)) ? "active" : (param.page.equals("".concat(counter.count - 1)) ? "warning accessible": "warning")))}">

                    <a onclick='${(((sessionScope.get("finishedSteps")[counter.count] == true && !param.page.equals("".concat(counter.count)))
                                || param.page.equals("".concat(counter.count-1))) ? "submitForm(".concat(counter.count).concat(")") : "")}'
                       class="${(param.page.equals("".concat(counter.count)) ? "scaled-step" : "")}">

                        <span class="circle">${counter.count}</span>
                        <span class="label">${pageName.toString().replace("[", "").replace("]", "")}</span>
                    </a>

                </li>
            </c:forEach>
        </ul>
    </div>
</div>

