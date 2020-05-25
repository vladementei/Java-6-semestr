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
            var form = document.getElementById('content-form');
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
    function submitForm(page) {
        var event = new Event('submit');
        event.initEvent('submit', true, true);
        console.log(event);
        document.getElementById('content-form').dispatchEvent(event);
        var form = document.getElementById('content-form');
        if (form.checkValidity() === true) {
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'page';
            input.value = page;
            form.appendChild(input);
            form.submit();
        }
    }
</script>
