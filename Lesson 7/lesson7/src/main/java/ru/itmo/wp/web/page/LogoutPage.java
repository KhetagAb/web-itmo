package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class LogoutPage extends AbstractPage {
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        setUser(null);

        redirect("/index", "Good bye. Hope to see you soon!");
    }
}
