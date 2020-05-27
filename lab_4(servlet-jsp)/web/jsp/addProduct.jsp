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
<jsp:setProperty name="product" property="name" value="${requestScope.name}" />
<jsp:setProperty name="product" property="description" value="${requestScope.description}" />

<div class="step-title">Create Product</div>
<div class="step-description">Fill Product Information</div>
<div class="step-form">
    <form id="add-product-form" method="post" novalidate>
        <div class="form-content d-flex justify-content-center scrollbar-primary">
            <div class="form-group">
                <label for="productName">Name</label>
                <input type="text" class="form-control" value="<jsp:getProperty property="name" name="product"/>"
                       id="productName" name="productName" placeholder="Pencil" required>
                <div class="invalid-feedback">
                    Please provide product name
                </div>
            </div>
            <div class="form-group">
                <label for="productDescription">Description</label>
                <input type="text" class="form-control" value="<jsp:getProperty property="description" name="product"/>"
                       id="productDescription" name="productDescription" placeholder="Beautiful, dark" required>
                <div class="invalid-feedback">
                    Please provide product description
                </div>
            </div>
        </div>
        <div class="dialog-action">
            <button type="button"  onclick="window.history.back()" class="btn">Close</button>
            <button type="button"  onclick="submitForm('add-product-form', 2)" class="btn">Save</button>
        </div>
    </form>
</div>
