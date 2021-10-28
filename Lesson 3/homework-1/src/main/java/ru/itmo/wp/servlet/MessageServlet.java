package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MessageServlet extends HttpServlet {
    private static final List<Message> messages = new ArrayList<>();

    private static boolean isOnlyWhitespaces(String string) {
        return string.trim().length() == 0;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object toJson = null;

        HttpSession session = request.getSession();
        String authedUser = (String) session.getAttribute("user");
        switch (request.getRequestURI()) {
            case "/message/auth":
                String user = request.getParameter("user");

                if (user != null && !isOnlyWhitespaces(user) && !user.equals(authedUser)) {
                    session.setAttribute("user", user);
                    toJson = user;
                } else if (authedUser != null) {
                    toJson = authedUser;
                }
                break;
            case "/message/add":
                String text = request.getParameter("text");
                if (isOnlyWhitespaces(text)) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                } else {
                    Message toAdd = new Message(authedUser, text);
                    messages.add(toAdd);
                    toJson = toAdd;
                }
                break;
            case "/message/findAll":
                if (authedUser != null && !messages.isEmpty()) {
                    toJson = messages;
                }
                break;
            default:
                return;
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().write(new Gson().toJson(toJson).getBytes(StandardCharsets.UTF_8));
        response.getWriter().flush();
    }
}

class Message {
    public final String user;
    public final String text;

    Message(String user, String text) {
        this.user = user;
        this.text = text;
    }
}