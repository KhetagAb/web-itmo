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
            String userId = request.getParameter("userId");
            try {
                long userTargetId = Long.parseLong(userId);
                view.put("user", userService.switchAdminRoot(userTargetId));
                if (userTargetId == authedUser.getId()) {
                    throw new ValidationException("mds");
                }
            } catch (NumberFormatException ignored) {
                throw new ValidationException("Invalid user id:" + userId);
            }
        } else {
            redirect("/users", "You must be admin");
        }
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
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
