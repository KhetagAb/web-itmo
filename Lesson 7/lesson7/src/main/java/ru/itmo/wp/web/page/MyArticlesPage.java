package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlesPage extends AbstractPage {
    private final ArticleService articleService = new ArticleService();
    private final UserService userService = new UserService();

    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        User user = getAuthorizedUser();
        view.put("articles", articleService.findAllByUser(user));
    }

    private void setArticleVisibility(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Article article = articleService.validateArticleId(request.getParameter("articleId"));
        String visibility = request.getParameter("visibility");
        if (visibility != null && (visibility.equals("hide") || visibility.equals("show"))) {
            userService.setArticleVisibility(getAuthorizedUser(), article, visibility.equals("hide"));
        } else {
            redirect("/index");
        }
    }
}
