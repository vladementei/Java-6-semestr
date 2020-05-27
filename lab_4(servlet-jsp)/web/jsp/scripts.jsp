<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 10.05.2020
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var form = document.getElementById('add-product-form');
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
                console.log(form.checkValidity());
            }, false);
        }, false);

        window.addEventListener('load', function () {
            var form = document.getElementById('add-warehouse-form');
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
                console.log(form.checkValidity());
            }, false);
        }, false);

        window.addEventListener('load', function () {
            var form = document.getElementById('add-remnant-form');
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
                console.log(form.checkValidity());
            }, false);
        }, false);
    })();
</script>

<script>
    window.addEventListener("load", function () {
        document.getElementById('warehouses-form').childNodes[1].childNodes.forEach(function (row) {
            if (row.nodeType === 1) {
                row.addEventListener("click", function (evt) {
                    if (!row.classList.contains('table-row-focus')) {
                        row.classList.replace('table-row', 'table-row-focus');
                        document.getElementById('warehouses-form').childNodes[1].childNodes.forEach(function (change) {
                            if (change.nodeType === 1 && change.classList.contains('table-row-focus') && change !== row) {
                                change.classList.replace('table-row-focus', 'table-row');
                            }
                        });
                    }
                });
            }
        });
        document.getElementById('warehouses-form').childNodes[1].childNodes[1].classList.replace('table-row', 'table-row-focus');


            document.getElementById('products-form').childNodes[1].childNodes.forEach(function (row) {
            if (row.nodeType === 1) {
                row.addEventListener("click", function (evt) {
                    if (!row.classList.contains('table-row-focus')) {
                        row.classList.replace('table-row', 'table-row-focus');
                        document.getElementById('products-form').childNodes[1].childNodes.forEach(function (change) {
                            if (change.nodeType === 1 && change.classList.contains('table-row-focus') && change !== row) {
                                change.classList.replace('table-row-focus', 'table-row');
                            }
                        });
                    }
                });
            }
        });
        document.getElementById('products-form').childNodes[1].childNodes[1].classList.replace('table-row', 'table-row-focus');
    });
        window.addEventListener("load", function () {
        document.getElementById('remnants-form').childNodes[1].childNodes.forEach(function (row) {
            if (row.nodeType === 1) {
                row.addEventListener("click", function (evt) {
                    if (!row.classList.contains('table-row-focus')) {
                        row.classList.replace('table-row', 'table-row-focus');
                        document.getElementById('remnants-form').childNodes[1].childNodes.forEach(function (change) {
                            if (change.nodeType === 1 && change.classList.contains('table-row-focus') && change !== row) {
                                change.classList.replace('table-row-focus', 'table-row');
                            }
                        });
                    }
                });
            }
        });
        document.getElementById('remnants-form').childNodes[1].childNodes[1].classList.replace('table-row', 'table-row-focus');

    });

    function getSelectedElementId(formName) {
        var answer;
        document.getElementById(formName).childNodes[1].childNodes.forEach(function (row) {
            if (row.nodeType === 1 && row.classList.contains('table-row-focus')) {
                answer = parseInt(row.childNodes[1].innerText.trim().toString());
            }
        });
        return answer;
    }

</script>

<script>
    function submitForm(formName, param) {
        var event = new Event('submit');
        event.initEvent('submit', true, true);
        document.getElementById(formName).dispatchEvent(event);
        var form = document.getElementById(formName);
        if (form.checkValidity() === true) {
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'param';
            input.value = param;
            form.appendChild(input);
            form.submit();
        }
    }
</script>
