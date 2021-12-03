package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostDto {
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    private String title;

    private String tagLine;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    private String text;
}
