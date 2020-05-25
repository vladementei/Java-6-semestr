<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 16.05.2020
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="step-title">Credit Card Networks</div>
<div class="step-description">Please choose network for your card</div>
<div class="step-form">
    <form id="content-form" method="post" novalidate>
        <div class="form-content scrollbar-primary">
            <div class="form-group">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="cardNetworkRadio" id="visa" value="visa"
                        <%=("visa").equals(session.getAttribute("cardNetworkRadio")) ? " checked" : ""%> checked>
                    <img src="../images/visa.png">
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="cardNetworkRadio" id="mastercard" value="mastercard"
                        <%=("mastercard").equals(session.getAttribute("cardNetworkRadio")) ? " checked" : ""%>>
                    <img src="../images/mastercard.png">
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="cardNetworkRadio" id="maestro" value="maestro"
                        <%=("maestro").equals(session.getAttribute("cardNetworkRadio")) ? " checked" : ""%>>
                    <img src="../images/maestro.png">
                </div>
            </div>
        </div>
        <div class="step-action mt-auto">
            <button type="button" onclick="submitForm(1)" class="btn">Back</button>
            <button type="button" onclick="submitForm(3)" class="btn">Next</button>
        </div>
    </form>
</div>
