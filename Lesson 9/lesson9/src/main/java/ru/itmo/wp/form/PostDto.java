package ru.itmo.wp.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostDto {
    @NotBlank
    @Size(min = 1, max = 60)
    private String title;

    @Size(max = 64)
    private String tagsLine;

    @NotBlank
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
