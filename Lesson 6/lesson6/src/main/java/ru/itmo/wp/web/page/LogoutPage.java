package ru.itmo.wp.web.page;

import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class LogoutPage extends AbstractPage {
    private final UserService userService = new UserService();

    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    protected void action(HttpServletRequest request, Map<String, Object> view) {
        userService.logout(getUser());

        setUser(null);
        redirect("/index", "Good bye. Hope to see you soon!");
    }
}
