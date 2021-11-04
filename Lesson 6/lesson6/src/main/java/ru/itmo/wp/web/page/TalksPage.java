package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.dto.TalkDto;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TalksPage extends AbstractPage {
    private final TalkService talkService = new TalkService();

    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        User user = getUser();
        if (user == null) {
            redirect("/index", "To enter Talks page please login.");
        }

        view.put("users", userService.findAll());

        List<Talk> allRelatedByUser = talkService.getAllRelatedByUser(user);
        List<TalkDto> talks = allRelatedByUser.stream()
                .map(talkService::convert)
                .collect(Collectors.toList());
        view.put("talks", talks);
    }

    protected void sendMessage(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String targetLogin = request.getParameter("targetLogin");
        String text = request.getParameter("text");

        User targetUser = talkService.validateSendMessage(getUser(), targetLogin, text);

        Talk talk = new Talk();
        talk.setSourceUserId(getUser().getId());
        talk.setTargetUserId(targetUser.getId());
        talk.setText(text);

        talkService.addTalk(talk);

        redirect("/talks", "Your message to " + targetLogin + " sent successfully!");
    }
}
