<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 16.05.2020
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld" %>
<%@ page errorPage="error.jsp" %>

<div class="step-title">Card summary</div>
<div class="step-description">Your credit card will look like this</div>
<div class="step-form">
    <form id="content-form" method="post" novalidate>
        <div class="form-content scrollbar-primary">
            <ex:card network='../images/${sessionScope.get("cardNetworkRadio")}.png'
                     design='../images/${sessionScope.get("cardDesignRadio")}.png'>
                ${sessionScope.get("cardholderName").concat(" ").concat(sessionScope.get("cardholderSurname"))}
            </ex:card>
            <%--<div class="summary">--%>
                <%--<img src="../images/<%=session.getAttribute("cardDesignRadio")%>.png">--%>
                <%--<span class="cardholder"><%=session.getAttribute("cardholderName") + " " + session.getAttribute("cardholderSurname")%></span>--%>
                <%--<img class="card-network" src="../images/<%=session.getAttribute("cardNetworkRadio")%>.png">--%>
            <%--</div>--%>
        </div>
        <div class="step-action mt-auto">
            <button type="button" onclick="submitForm(3)" class="btn">Back</button>
        </div>
    </form>
</div>
