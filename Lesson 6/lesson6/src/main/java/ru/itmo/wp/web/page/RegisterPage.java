package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class RegisterPage extends AbstractPage {
    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        if (getUser() != null) {
            redirect("/index", "You are already log in");
        }
    }

    private void register(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setEmail(request.getParameter("email"));
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");

        userService.validateRegistration(user, password, passwordConfirmation);
        userService.register(user, password);

        redirect("/index", "You are successfully registered!");
    }
}
