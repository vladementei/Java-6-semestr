<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 26.05.2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id = "product" class = "by.dementei.entity.Product"/>
<jsp:useBean id = "warehouse" class = "by.dementei.entity.Warehouse"/>
<jsp:useBean id = "remnant" class = "by.dementei.entity.Remnant"/>

<jsp:setProperty name="product" property="name" value="${requestScope.name}" />
<jsp:setProperty name="product" property="description" value="${requestScope.description}" />
<jsp:setProperty name="warehouse" property="title" value="${requestScope.title}" />
<jsp:setProperty name="warehouse" property="location" value="${requestScope.location}" />


<div class="step-title">Create Remnant</div>
<div class="step-description">Fill Remnant Information</div>
<div class="step-form">
    <form id="add-remnant-form" method="post" novalidate>
        <div class="form-content d-flex justify-content-center scrollbar-primary">
            <div class="form-group">
                <label for="remnantProduct">Product</label>
                <span id="remnantProduct">name = <jsp:getProperty property="name" name="product"/>; description = <jsp:getProperty property="description" name="product"/></span>
            </div>
            <div class="form-group">
                <label for="remnantWarehouse">Warehouse</label>
                <span id="remnantWarehouse">title = <jsp:getProperty property="title" name="warehouse"/>; location = <jsp:getProperty property="location" name="warehouse"/></span>
            </div>
            <div class="form-group">
                <label for="remnantAmount">Amount</label>
                <input type="text" class="form-control" value="<jsp:getProperty property="amount" name="remnant"/>"
                       id="remnantAmount" name="remnantAmount" placeholder="12" required>
                <div class="invalid-feedback">
                    Please provide remnant amount
                </div>
            </div>
        </div>
        <div class="dialog-action">
            <button type="button"  onclick="window.history.back()" class="btn">Close</button>
            <button type="button"  onclick="submitForm('add-remnant-form', 2)" class="btn">Save</button>
        </div>
    </form>
</div>
