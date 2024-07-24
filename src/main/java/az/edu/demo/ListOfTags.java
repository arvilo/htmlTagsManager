package az.edu.demo;

import java.io.Serializable;
import java.util.HashMap;

public class ListOfTags implements Serializable {

    private static final Long serialVersionUID = 1L;
    public HashMap<Integer, HtmlTag> tags;

    public ListOfTags() {
        tags = new HashMap<>();
    }
}
