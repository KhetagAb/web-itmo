package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class UsersPage extends AbstractPage {
    private final UserService userService = new UserService();

    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        view.put("authedUser", getUser());
    }

    private void switchAdminRoot(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User authedUser = getAuthorizedUser();
        if (authedUser.isAdmin()) {
            long userId = userService.validateUserId(request.getParameter("userId"));
            view.put("user", userService.switchAdminRoot(userId));
            if (userId == authedUser.getId()) {
                redirect("/users", "You have changed your admin roots");
            }
        } else {
            redirect("/users", "You must have admin roots");
        }
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        long userId = userService.validateUserId(request.getParameter("userId"));

        view.put("user", userService.findById(userId));
    }
}
