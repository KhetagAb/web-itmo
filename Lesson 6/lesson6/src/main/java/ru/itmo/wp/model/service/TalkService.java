package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.dto.TalkDto;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

import java.util.List;

public class TalkService {
    private final TalkRepository talkRepository = new TalkRepositoryImpl();
    private final UserService userService = new UserService();

    public User validateSendMessage(User source, String targetLogin, String text) throws ValidationException {
        if (text == null || text.isBlank()) {
            throw new ValidationException("Text is required");
        }
        if (text.length() > 2000) {
            throw new ValidationException("Text can't be longer than 2000 characters");
        }

        if (source == null) {
            throw new ValidationException("Nobody cannot send message");
        }
        if (source.getLogin().equals(targetLogin)) {
            throw new ValidationException("Cannot send message from " + source.getLogin() + " to himself");
        }

        userService.validateLogin(targetLogin);
        User targetUser = userService.findUserByLogin(targetLogin);
        if (targetUser == null) {
            throw new ValidationException("User with login " + targetLogin + " doesn't exist");
        } else {
            return targetUser;
        }
    }

    public Talk addTalk(Talk talk) {
        return talkRepository.save(talk);
    }

    public List<Talk> getAllRelatedByUser(User user) {
        return talkRepository.findAllRelatedByUserId(user.getId());
    }

    public TalkDto convert(Talk talk) {
        return new TalkDto(
                talk.getId(),
                userService.findUserById(talk.getSourceUserId()).getLogin(),
                userService.findUserById(talk.getTargetUserId()).getLogin(),
                talk.getText(),
                talk.getCreationTime());
    }
}
