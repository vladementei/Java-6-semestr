<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 16.05.2020
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="step-title">Cardholder information</div>
<div class="step-description">Please enter your first and last name</div>
<div class="step-form">
    <form id="content-form" method="post" novalidate>
        <div class="form-content scrollbar-primary">
            <div class="form-group">
                <label for="cardholderName">First name</label>
                <input type="text" class="form-control" value="<%=session.getAttribute("cardholderName") != null ? session.getAttribute("cardholderName") : ""%>"
                       id="cardholderName" name="cardholderName" placeholder="Ivan" required>
                <div class="invalid-feedback">
                    Please provide cardholder first name
                </div>
            </div>
            <div class="form-group">
                <label for="cardholderSurname">Last name</label>
                <input type="text" class="form-control" value="<%=session.getAttribute("cardholderSurname") != null ? session.getAttribute("cardholderSurname") : ""%>"
                       id="cardholderSurname" name="cardholderSurname" placeholder="Petrov" required>
                <div class="invalid-feedback">
                    Please provide cardholder last name
                </div>
            </div>
        </div>
        <div class="step-action mt-auto">
            <button type="button" onclick="submitForm(2)" class="btn">Next</button>
        </div>
    </form>
</div>
