package by.dementei;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


public class MainJSP extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        cleanSession(request, session);
        request.getRequestDispatcher("/").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("cardholderName") != null) {
            session.setAttribute("cardholderName", request.getParameter("cardholderName"));
            session.setAttribute("cardholderSurname", request.getParameter("cardholderSurname"));
            ((boolean[])session.getAttribute("finishedSteps"))[1] = true;
        } else if (request.getParameter("cardNetworkRadio") != null) {
            session.setAttribute("cardNetworkRadio", request.getParameter("cardNetworkRadio"));
            ((boolean[])session.getAttribute("finishedSteps"))[2] = true;
        } else if (request.getParameter("cardDesignRadio") != null) {
            session.setAttribute("cardDesignRadio", request.getParameter("cardDesignRadio"));
            ((boolean[])session.getAttribute("finishedSteps"))[3] = true;
            ((boolean[])session.getAttribute("finishedSteps"))[4] = true;
        }
        doGet(request, response);
    }

    private HttpSession cleanSession(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("finishedSteps") == null) {
            session = request.getSession();
            session.setAttribute("finishedSteps", new boolean[5]);
        }
        return session;
    }
}

