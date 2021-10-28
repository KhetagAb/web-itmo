package ru.itmo.web.lesson4.web;

import freemarker.template.*;
import ru.itmo.web.lesson4.util.DataUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerServlet extends HttpServlet {
    private static final String UTF_8 = StandardCharsets.UTF_8.name();
    private final Configuration freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_30);

    @Override
    public void init() throws ServletException {
        super.init();

        File dir = new File(getServletContext().getRealPath("."), "../../src/main/webapp/WEB-INF/templates");
        try {
            freemarkerConfiguration.setDirectoryForTemplateLoading(dir);
        } catch (IOException e) {
            throw new ServletException("Unable to set template directory [dir=" + dir + "].", e);
        }

        freemarkerConfiguration.setDefaultEncoding(UTF_8);
        freemarkerConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        freemarkerConfiguration.setLogTemplateExceptions(false);
        freemarkerConfiguration.setWrapUncheckedExceptions(true);
        freemarkerConfiguration.setFallbackOnNullLoopVariable(false);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);

        String uri = request.getRequestURI();
        if (uri.isEmpty() || uri.equals("/")) {
//            response.sendRedirect("/index.html");
            response.sendRedirect("/index");
        } else {
            Template template;
            try {
                template = freemarkerConfiguration.getTemplate(URLDecoder.decode(uri, UTF_8) + ".ftlh");
            } catch (TemplateNotFoundException ignored) {
                template = freemarkerConfiguration.getTemplate("not_found.ftlh");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

            Map<String, Object> data = getData(request);

            response.setContentType("text/html");
            try {
                template.process(data, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    private Map<String, Object> getData(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();

        for (Map.Entry<String, String[]> element : request.getParameterMap().entrySet()) {
            if (element.getValue() != null && element.getValue().length == 1) {
                if (element.getKey().endsWith("_id")) {
                    try {
                        data.put(element.getKey(), Long.parseLong(element.getValue()[0]));
                    } catch (NumberFormatException ignored) {}
                } else {
                    data.put(element.getKey(), element.getValue()[0]);
                }
            }
        }

        DataUtil.addData(request, data);

        return data;
    }
}
