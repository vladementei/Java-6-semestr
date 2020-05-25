package by.dementei;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class CardTag extends SimpleTagSupport {
    private String network;
    private String design;

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        StringWriter sw = new StringWriter();
        getJspBody().invoke(sw);
        String cardholder = sw.toString();
        if(cardholder.trim().split(" ").length > 1) {
            out.println("<div class=\"summary\">\n" +
                        "    <img src=\"" + design + "\">\n" +
                        "    <span class=\"cardholder\">" + cardholder + "</span>\n" +
                        "    <img class=\"card-network\" src=\"" + network + "\">\n" +
                        "</div>");
        } else {
            throw new CardholderException("Cardholder name is Wrong!!! Please provide name and surname!");
        }
    }
}
