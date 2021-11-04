package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;
import ru.itmo.wp.web.exception.SessionNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public abstract class AbstractPage {
    private HttpSession session = null;
    protected final UserService userService = new UserService();

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        session = request.getSession();

        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }

        String message = getMessage();
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }

        view.put("userCount", userService.findCount());
    }

    protected void action(HttpServletRequest request, Map<String, Object> view) {
        // Nothing to do;
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        // Nothing to do;
    }

    protected void redirect(String action, String message) {
        setMessage(message);
        redirect(action);
    }

    protected void redirect(String action) {
        throw new RedirectException(action);
    }

    private HttpSession getSession() {
        if (session == null) {
            throw new SessionNotFoundException("Session not found");
        }
        return session;
    }

    protected String getMessage() {
        return (String) getSession().getAttribute("message");
    }

    protected User getUser() {
        return (User) getSession().getAttribute("user");
    }

    protected void setMessage(String message) {
        if (message == null) {
            getSession().removeAttribute("message");
        } else {
            getSession().setAttribute("message", message);
        }
    }

    protected void setUser(User user) {
        if (user == null) {
            getSession().removeAttribute("user");
        } else {
            getSession().setAttribute("user", user);
        }
    }
}
