package ru.itmo.wp.web.page;

import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class UsersPage extends AbstractPage {
    private final UserService userService = new UserService();

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("user", getUser());
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String userId = request.getParameter("userId");

        try {
            long id = Long.parseLong(userId);
            view.put("user", userService.findById(id));
        } catch (NumberFormatException ignored) {
            throw new ValidationException("Invalid user id:" + userId);
        }
    }
}
