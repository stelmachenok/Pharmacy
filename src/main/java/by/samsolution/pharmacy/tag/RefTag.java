package by.samsolution.pharmacy.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RefTag extends SimpleTagSupport implements DynamicAttributes {
    private Map<String, Object> map = new HashMap<String, Object>();

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String ref = "";
        if (map.get("pageContext") != null) ref += map.get("pageContext");
        if (map.get("lang") != null) ref += "?lang=" + map.get("lang");
        if (map.get("sortField") != null) ref += "?sort-field=" + map.get("sortField");
        if (map.get("sortDir") != null) ref += "&sort-direction=" + map.get("sortDir");
        if (map.get("pageNum") != null) ref += "&page-num=" + map.get("pageNum");
        if (map.get("pageSize") != null) ref += "&page-size=" + map.get("pageSize");
        if (map.get("action") != null) ref += "&action=" + map.get("action");
        if (map.get("id") != null) ref += "&id=" + map.get("id");
        out.println(ref);
    }

    @Override
    public void setDynamicAttribute(String s, String s1, Object o) throws JspException {
        map.put(s1, o);
    }
}
