package ru.itmo.wp.lesson8.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NoticeDto {
    @NotBlank
    @Size(min = 5, max = 200, message = "The text must have more 16 and less than 200 symbols")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
