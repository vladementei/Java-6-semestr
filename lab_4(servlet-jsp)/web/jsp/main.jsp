<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 26.05.2020
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="content">
    <div class="d-flex flex-column">
        <div class="step-description">Warehouses</div>
        <div class="step-form">
            <form id="warehouses-form">
                <div class="form-content scrollbar-primary">
                    <c:if test="${not empty warehouses}">
                        <c:forEach items="${warehouses}" var="warehouse">
                            <div class="table-row" ondblclick="document.location.href='/main?action=openWarehouse&id=${warehouse.id}'"
                                 oncontextmenu="document.location.href='/main?action=deleteWarehouse&id=${warehouse.id}'; return false;">
                                <div class="id">${warehouse.id}</div>
                                <div>${warehouse.title}</div>
                                <div>${warehouse.location}</div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
    <div class="d-flex flex-column">
        <div class="step-description">Products</div>
        <div class="step-form">
            <form id="products-form">
                <div class="form-content scrollbar-primary">
                    <c:if test="${not empty products}">
                        <c:forEach items="${products}" var="product">
                            <div class="table-row" ondblclick="document.location.href='/main?action=openProduct&id=${product.id}'"
                                 oncontextmenu="document.location.href='/main?action=deleteProduct&id=${product.id}'; return false;">
                                <div class="id">${product.id}</div>
                                <div>${product.name}</div>
                                <div>${product.description}</div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="step-action mt-auto">
    <button type="button" onclick="document.location.href='/main?action=addWarehouse'" class="btn">Add Warehouse</button>
    <button type="button" onclick='document.location.href=("/main?action=addRemnant&productId="+ getSelectedElementId("products-form")
    + "&warehouseId="+ getSelectedElementId("warehouses-form"))' class="btn">Add Remnant</button>
    <button type="button" onclick="document.location.href='/main?action=addProduct'" class="btn">Add Product</button>
</div>
