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

    public void validateSave(String title, String text) throws ValidationException {
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

    public long validateArticleId(String articleId) throws ValidationException {
        try {
            return Long.parseLong(articleId);
        } catch (NumberFormatException ignored) {
            throw new ValidationException("Invalid article id: " + articleId);
        }
    }

    public Article save(User user, String title, String text) {
        return save(user, title, text, true);
    }

    public Article save(User user, String title, String text, boolean isHidden) {
        Article article = new Article();

        article.setUserId(user.getId());
        article.setTitle(title);
        article.setText(text);
        article.setHidden(isHidden);

        return articleRepository.save(article);
    }

    public List<Article> findAllByHiddenOrderedByCreationTime(boolean isHidden) {
        return articleRepository.findAllByHiddenOrderedByCreationTime(isHidden);
    }

    public List<Article> findAllByUser(User user) {
        return articleRepository.findAllByUserId(user.getId());
    }

    public Article switchVisibility(Article article) {
        article.setHidden(!article.isHidden());
        return articleRepository.update(article);
    }

    public Article findById(long id) {
        return articleRepository.findById(id);
    }
}
