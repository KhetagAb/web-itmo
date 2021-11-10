package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class RegisterPage extends AbstractPage {
    private final UserService userService = new UserService();

    private void register(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        String password = request.getParameter("password");

        userService.validateRegistration(user, password);
        userService.register(user, password);

        redirect("/index", "You are successfully registered!");
    }
}
