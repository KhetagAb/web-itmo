package ru.itmo.wp.web.page;

import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class LogoutPage extends AbstractPage {
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        setUser(null);
        setMessage("Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
