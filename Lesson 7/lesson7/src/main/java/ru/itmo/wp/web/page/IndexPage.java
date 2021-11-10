package ru.itmo.wp.web.page;

import ru.itmo.wp.model.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class IndexPage extends AbstractPage {
    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", new ArticleService().findAll());
    }
}
