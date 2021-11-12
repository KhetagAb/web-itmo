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
    private final UserService userService = new UserService();
    private HttpSession session = null;

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        session = request.getSession();

        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }

        String message = getMessage();
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            setMessage(null);
        }
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

    protected HttpSession getSession() {
        if (session == null) {
            throw new SessionNotFoundException("Session not found");
        }
        return session;
    }

    private String getMessage() {
        return (String) getSession().getAttribute("message");
    }

    private void setMessage(String message) {
        if (message == null) {
            getSession().removeAttribute("message");
        } else {
            getSession().setAttribute("message", message);
        }
    }

    protected User getAuthorizedUser() {
        User user = getUser();

        if (user == null) {
            redirect("/index", "You need to login first");
        }
        return user;
    }

    protected User getUser() {
        User user = (User) getSession().getAttribute("user");
        if (user != null) {
            user = userService.findById(user.getId());
            setUser(user);
        }
        return user;
    }

    protected void setUser(User user) {
        if (user == null) {
            getSession().removeAttribute("user");
        } else {
            getSession().setAttribute("user", user);
        }
    }
}
