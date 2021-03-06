package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.dto.ArticleDto;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

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

    public Article validateArticleId(String articleId) throws ValidationException {
        try {
            Article article = findById(Long.parseLong(articleId));
            if (article != null) {
                return article;
            } else {
                throw new ValidationException("Cannot find article with id: " + articleId);
            }
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

    public List<ArticleDto> findAllByHiddenOrderedByCreationTime(boolean isHidden) {
        List<Article> allByHiddenOrderedByCreationTime = articleRepository.findAllByHiddenOrderedByCreationTime(isHidden);
        return allByHiddenOrderedByCreationTime.stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    public List<Article> findAllByUser(User user) {
        return articleRepository.findAllByUserId(user.getId());
    }

    public Article setArticleVisibility(Article article, boolean isHidden) {
        article.setHidden(isHidden);
        return articleRepository.update(article);
    }

    public Article findById(long id) {
        return articleRepository.findById(id);
    }
}
