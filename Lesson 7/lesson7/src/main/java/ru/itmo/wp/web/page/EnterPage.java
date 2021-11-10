package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class EnterPage extends AbstractPage {
    private final UserService userService = new UserService();
    private final ArticleService articleService = new ArticleService();

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.validateAndFindByLoginAndPassword(login, password);
        setUser(user);

        redirect("/index", "Hello, " + user.getLogin());
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleService.findAll());
    }
}
