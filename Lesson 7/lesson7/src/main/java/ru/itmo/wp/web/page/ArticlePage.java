package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage extends AbstractPage {
    private final ArticleService articleService = new ArticleService();

    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        User user = getUser();
        if (user == null) {
            redirect("/index", "To enter Article page please login.");
        }
    }

    private void createArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");

        articleService.validateCreateArticle(title, text);
        articleService.createArticle(getUser(), title, text);

        redirect("/index", "Article created");
    }
}
