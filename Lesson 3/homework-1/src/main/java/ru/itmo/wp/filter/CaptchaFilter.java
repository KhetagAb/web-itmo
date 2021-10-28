package ru.itmo.wp.filter;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;

public class CaptchaFilter extends HttpFilter {
    private final int MIN_CAPTCHA_VALUE = 100;
    private final int MAX_CAPTCHA_VALUE = 1000;
    private final String CAPTCHA_VALUE = "captchaValue";
    private final String IS_CAPTCHA_CHECKED = "captchaChecked";

    public int getRandomInteger(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            HttpSession session = request.getSession();

            boolean isCaptchaChecked = (boolean) Objects.requireNonNullElse(session.getAttribute(IS_CAPTCHA_CHECKED), false);
            if (!isCaptchaChecked) {
                String captchaValue = (String) session.getAttribute(CAPTCHA_VALUE);
                String receivedValue = request.getParameter(CAPTCHA_VALUE);

                if (captchaValue != null && captchaValue.equals(receivedValue)) {
                    session.setAttribute(IS_CAPTCHA_CHECKED, true);
                } else {
                    if (captchaValue == null || receivedValue != null) {
                        captchaValue = Integer.toString(getRandomInteger(MIN_CAPTCHA_VALUE, MAX_CAPTCHA_VALUE));
                        session.setAttribute(CAPTCHA_VALUE, captchaValue);
                    }
                    showCaptcha(captchaValue, request.getRequestURI(), response);
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    private void showCaptcha(String captchaValue, String returnUri, HttpServletResponse response) throws IOException {
        Path PATH_TO_FORM_HTML = Path.of(getServletContext().getRealPath("static/captcha_form.html"));
        String htmlBody = Files.readString(PATH_TO_FORM_HTML);

        byte[] imageBytes = ImageUtils.toPng(captchaValue);
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        String formattedString = String.format(htmlBody, base64Image, returnUri, CAPTCHA_VALUE, CAPTCHA_VALUE);
        byte[] htmlBytes =  formattedString.getBytes(StandardCharsets.UTF_8);

        response.setContentType("text/html");
        try (ServletOutputStream printWriter = response.getOutputStream()) {
            printWriter.write(htmlBytes);
        }
    }
}
