package src.taglib.form;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

import framework.Validator;



public class FieldTag extends SimpleTagSupport implements DynamicAttributes
{
    private String name;
    private String label;
    private String type = "text";
    private boolean required;
    private HashMap<String, String> tagAttributes = new HashMap<String, String>();



    public void setDynamicAttribute(String uri, String name, Object value) throws JspException
    {
        tagAttributes.put(name, value.toString());
    }



    @Override
    public void doTag() throws JspException, IOException
    {
        HttpServletRequest request = ((HttpServletRequest) ((PageContext) getJspContext()).getRequest());
        JspWriter out = getJspContext().getOut();
        Validator v = (Validator) request.getAttribute("errors");

        // Construit le champ
        out.println("<p>");
        out.println("    <label for=\"form_" + name + "\">" + label + "</label>");
        out.print(  "    <input type=\"" + type + "\" name=\"" + name + "\" id=\"form_" + name + "\"");

            if(required)
                out.print(" required");

            if(request.getParameter(name) != null)
                out.print(" value=\"" + request.getParameter(name).trim() + "\"");

            for(Entry<String, String> attr : tagAttributes.entrySet())
                out.print(" " + attr.getKey() + "=\"" + attr.getValue() + "\"");

            out.println(">");

        if(v != null && v.hasError(name))
            out.println("    <span class=\"form-error\">" + v.getError(name) + "</span>");

        out.println("</p>");
    }



    public void setName(String name)
    {
        this.name = name;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setRequired(Boolean required)
    {
        this.required = required;
    }
}