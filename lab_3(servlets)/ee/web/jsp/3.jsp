<%--
  Created by IntelliJ IDEA.
  User: NotePad.by
  Date: 16.05.2020
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="step-title">Card Design</div>
<div class="step-description">Please choose design for your card</div>
<div class="step-form">
    <form id="content-form" method="post" novalidate>
        <div class="form-content scrollbar-primary grid-2-columns">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="cardDesignRadio" id="1" value="card1" checked
                    <%=("card1").equals(session.getAttribute("cardDesignRadio")) ? " checked" : ""%> >
                <img src="../images/card1.png">
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="cardDesignRadio" id="2" value="card2"
                    <%=("card2").equals(session.getAttribute("cardDesignRadio")) ? " checked" : ""%> >
                <img src="../images/card2.png">
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="cardDesignRadio" id="3" value="card3"
                    <%=("card3").equals(session.getAttribute("cardDesignRadio")) ? " checked" : ""%> >
                <img src="../images/card3.png">
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="cardDesignRadio" id="4" value="card4"
                    <%=("card4").equals(session.getAttribute("cardDesignRadio")) ? " checked" : ""%> >
                <img src="../images/card4.png">
            </div>
        </div>
        <div class="step-action mt-auto">
            <button type="button" onclick="submitForm(2)" class="btn">Back</button>
            <button type="button" onclick="submitForm(4)" class="btn">Next</button>
        </div>
    </form>
</div>
