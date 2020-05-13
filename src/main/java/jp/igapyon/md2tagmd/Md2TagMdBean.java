package jp.igapyon.md2tagmd;

import java.util.ArrayList;
import java.util.List;

public class Md2TagMdBean {
    private String name;
    private String title;
    private List<String> tagList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String mdName) {
        this.name = mdName;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTags(String tags) {
        String[] tagsary = tags.split(",");
        getTagList().clear();
        for (String look : tagsary) {
            getTagList().add(look.trim());
        }
    }

    public String getTags() {
        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (String look : getTagList()) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }
            builder.append(look);
        }
        return builder.toString();
    }
}
