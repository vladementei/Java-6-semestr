<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 26.05.2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id = "warehouse" class = "by.dementei.entity.Warehouse"/>
<jsp:setProperty name="warehouse" property="title" value="${requestScope.title}" />
<jsp:setProperty name="warehouse" property="location" value="${requestScope.location}" />

<div class="step-title">Create Warehouse</div>
<div class="step-description">Fill Warehouse Information</div>
<div class="step-form">
    <form id="add-warehouse-form" method="post" novalidate>
        <div class="form-content d-flex justify-content-center scrollbar-primary">
            <div class="form-group">
                <label for="warehouseTitle">Title</label>
                <input type="text" class="form-control" value="<jsp:getProperty property="title" name="warehouse"/>"
                       id="warehouseTitle" name="warehouseTitle" placeholder="Main" required>
                <div class="invalid-feedback">
                    Please provide warehouse title
                </div>
            </div>
            <div class="form-group">
                <label for="warehouseLocation">Location</label>
                <input type="text" class="form-control" value="<jsp:getProperty property="location" name="warehouse"/>"
                       id="warehouseLocation" name="warehouseLocation" placeholder="Nezavisimosti 4" required>
                <div class="invalid-feedback">
                    Please provide warehouse location
                </div>
            </div>
        </div>
        <div class="dialog-action">
            <button type="button"  onclick="window.history.back()" class="btn">Close</button>
            <button type="button"  onclick="submitForm('add-warehouse-form', 2)" class="btn">Save</button>
        </div>
    </form>
</div>
