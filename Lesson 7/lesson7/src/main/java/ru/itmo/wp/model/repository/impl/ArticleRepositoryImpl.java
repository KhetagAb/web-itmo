package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.wrapper.ArticleWrapper;
import ru.itmo.wp.model.repository.wrapper.Wrapper;

import java.util.List;

public class ArticleRepositoryImpl extends AbstractRepositoryImpl<Article> implements ArticleRepository {
    private static final Wrapper<Article> ARTICLE_WRAPPER = new ArticleWrapper();
    private static final String TABLE_NAME = "Article";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Wrapper<Article> getElementWrapper() {
        return ARTICLE_WRAPPER;
    }

    @Override
    public List<Article> findAllByHiddenOrderedByCreationTime(boolean isHidden) {
        return findAllByArguments(new String[]{"isHidden"}, new Object[]{isHidden}, "creationTime");
    }

    @Override
    public List<Article> findAllByUserId(long id) {
        return findAllByArguments(new String[]{"userId"}, new Object[]{id});
    }
}
