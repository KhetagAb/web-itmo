package ru.itmo.wp.model.dto;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.service.UserService;

import java.util.Date;

public class ArticleDto {
    private static final UserService userService = new UserService();

    private final long id;
    private final String login;
    private final String title;
    private final String text;
    private final boolean hidden;
    private final Date creationTime;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.login = userService.findById(article.getUserId()).getLogin();
        this.title = article.getTitle();
        this.text = article.getText();
        this.hidden = article.isHidden();
        this.creationTime = article.getCreationTime();
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean isHidden() {
        return hidden;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
