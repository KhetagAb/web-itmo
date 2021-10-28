package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        String[] uris = uri.split("\\+");
        List<File> files = Arrays.stream(uris).map(this::getFile).collect(Collectors.toList());
        if (files.stream().anyMatch(Predicate.not(File::isFile))) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType(getContentTypeFromName(uris[0]));
        OutputStream outputStream = response.getOutputStream();
        for (File file : files) {
            Files.copy(file.toPath(), outputStream);
            outputStream.flush();
        }
    }

    private File getFile(String uri) {
        if (!uri.startsWith("/")) {
            uri = "/" + uri;
        }

        File file = new File( getServletContext().getRealPath("/") + "../../src/main/webapp/static", uri);
        if (file.isFile()) {
            return file;
        } else {
            return new File(getServletContext().getRealPath("/static" + uri));
        }
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
