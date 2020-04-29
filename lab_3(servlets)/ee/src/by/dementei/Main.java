package by.dementei;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        ServletContext context = getServletContext();
        response.setContentType("text/html");
        StringBuilder document = readFile(context.getResourceAsStream("head.html"));
        document.append("<body>\n");
        document.append(readFile(context.getResourceAsStream("navigation.html")));
        HttpSession session = request.getSession();
        session = cleanSession(request, session);
        initNavigationValues(document, request, session);
        document.append(readFile(context.getResourceAsStream((request.getParameter("page") != null ? request.getParameter("page") : "1") + ".html")));
        document.append("</body>\n</html>\n");
        initDefaultValues(document, request);
        printWriter.write(document.toString());
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

    private StringBuilder readFile(InputStream is) throws IOException{
        StringBuilder text = new StringBuilder();
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        }
        return text;
    }

    private HttpSession cleanSession(HttpServletRequest request, HttpSession session) {
        if (request.getParameter("page") == null) {
            session.invalidate();
            session = request.getSession();
            session.setAttribute("finishedSteps", new boolean[5]);
        }
        return session;
    }

    private void initDefaultValues(StringBuilder document, HttpServletRequest request) {
        if ("1".equals(request.getParameter("page")) && request.getSession().getAttribute("cardholderName") != null){
            document.insert(document.indexOf("id=\"cardholderName\"") - 1, "value=\"" + request.getSession().getAttribute("cardholderName") + "\"");
            document.insert(document.indexOf("id=\"cardholderSurname\"") - 1, "value=\"" + request.getSession().getAttribute("cardholderSurname") + "\"");
        } else if ("2".equals(request.getParameter("page")) && request.getSession().getAttribute("cardNetworkRadio") != null){
            document.insert(document.indexOf("value=\"" + request.getSession().getAttribute("cardNetworkRadio") + "\"") - 1, "checked");
        } else if ("3".equals(request.getParameter("page")) && request.getSession().getAttribute("cardDesignRadio") != null){
            document.insert(document.indexOf("value=\"" + request.getSession().getAttribute("cardDesignRadio") + "\"") - 1, "checked");
        } else if ("4".equals(request.getParameter("page"))) {
            document.insert(document.indexOf("class=\"cardholder\">") + 20, request.getSession().getAttribute("cardholderName").toString() + " " + request.getSession().getAttribute("cardholderSurname").toString());
            document.insert(document.indexOf("\"card-network\" src=\"images/") + 27, request.getSession().getAttribute("cardNetworkRadio"));
            document.insert(document.indexOf("<img src=\"images/") + 17, request.getSession().getAttribute("cardDesignRadio"));
        }
    }

    private void initNavigationValues(StringBuilder document, HttpServletRequest request, HttpSession session) {
        int li = 0;
        int a = 0;
        int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
        for (int i = 1; i <= 4; i++) {
            li = document.indexOf("<li ", li) + 4;
            if (((boolean[])session.getAttribute("finishedSteps"))[i] && i != page) {
                document.insert(li, "class=\"completed accessible\"");
                a = document.indexOf("<a ", a) + 3;
                document.insert(a, "onclick=\"submitForm(" + i + ")\"");
            } else if (i == page) {
                document.insert(li, "class=\"active\"");
                a = document.indexOf("<a ", a) + 3;
                document.insert(a, "class=\"scaled-step\"");
            } else if (i == page + 1) {
                document.insert(li, "class=\"warning accessible\"");
                a = document.indexOf("<a ", a) + 3;
                document.insert(a, "onclick=\"submitForm(" + i +")\"");
            } else {
                document.insert(li, "class=\"warning\"");
                a = document.indexOf("<a ", a) + 3;
            }
        }
    }
}
