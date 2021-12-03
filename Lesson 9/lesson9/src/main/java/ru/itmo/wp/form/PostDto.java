package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostDto {
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    private String title;

    private String tagsLine;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagsLine() {
        return tagsLine;
    }

    public void setTagsLine(String tagsLine) {
        this.tagsLine = tagsLine;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
