package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository extends AbstractRepository<Article> {
    Article findById(long id);

    List<Article> findAllByHiddenOrderedByCreationTime(boolean isHidden);

    List<Article> findAllByUserId(long id);

    Article update(Article element);
}
