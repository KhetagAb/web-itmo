package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateCreateArticle(String title, String text) throws ValidationException {
        if (Strings.isNullOrEmpty(title)) {
            throw new ValidationException("Title is required");
        }
        if (title.length() > 255) {
            throw new ValidationException("Title can't be longer than 255 letters");
        }

        if (Strings.isNullOrEmpty(text)) {
            throw new ValidationException("Text is required");
        }
        if (text.length() < 200) {
            throw new ValidationException("Text can't be shorter than 200 characters");
        }
        if (text.length() > 2000) {
            throw new ValidationException("Text can't be longer than 2000 characters");
        }
    }

    public Article createArticle(User user, String title, String text) {
        if (user != null) {
            Article article = new Article();

            article.setUserId(user.getId());
            article.setTitle(title);
            article.setText(text);

            return articleRepository.save(article);
        } else {
            throw new IllegalStateException("To create article user must be not null");
        }
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
