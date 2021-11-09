package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

public interface ArticleRepository {
    Article find(long id);

    void save(Article article);
}
