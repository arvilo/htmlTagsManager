package az.edu.demo;

import java.io.Serializable;
import java.util.HashMap;

public class HtmlTag implements Serializable {

    private static final Long serialVersionUID = 1L;
    private String tagname;
    private String content;
    private HashMap<String, String> style;

    public HtmlTag(String tagname, String content) {
        this.tagname = tagname;
        this.content = content;
        style = new HashMap<>();
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HashMap<String, String> getStyle() {
        return style;
    }

    public void setCssStyle(String style, String value) {
        this.style.put(style, value);
    }

    public void removeCssStyle(String style) {
        this.style.remove(style);
    }

    public String stringfy() {
        StringBuilder text = new StringBuilder("<");
        text.append(tagname);
        text.append(" ");
        text.append("style=\"");
        style.entrySet().stream()
                .forEach(entry ->
                        text.append(String.format("%s: %s;", entry.getKey(), entry.getValue())));
        text.append("\">");
        text.append(content);
        text.append("</");
        text.append(tagname);
        text.append(">");

        return text.toString();
    }
}
