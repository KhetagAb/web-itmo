package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage extends AbstractPage {
    private void createArticle(HttpServletRequest request, Map<String, Object> view) {
        String title = request.getParameter("title");
        String text = request.getParameter("text");

//        User user = userService.validateAndFindByLoginAndPassword(login, password);
//        request.getSession().setAttribute("user", user);
//        request.getSession().setAttribute("message", "Hello, " + user.getLogin());

        redirect("/index");
    }
}
