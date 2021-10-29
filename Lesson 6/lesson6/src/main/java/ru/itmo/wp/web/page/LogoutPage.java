package ru.itmo.wp.web.page;

import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class LogoutPage extends AbstractPage {
    private final UserService userService = new UserService();

    protected void action(HttpServletRequest request, Map<String, Object> view) {
        userService.logout(getUser());

        setUser(null);
        setMessage("Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
