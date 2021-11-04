package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class EnterPage extends AbstractPage {
    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        if (getUser() != null) {
            redirect("/index", "You are already log in");
        }
    }

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");

        User user = userService.validateAndEnter(loginOrEmail, password);

        setUser(user);
        redirect("/index", "Hello, " + user.getLogin());
    }
}
