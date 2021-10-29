package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @noinspection UnstableApiUsage
 */
public class UserService {
    private static final String PASSWORD_SALT = "177d4b5f2e4f4edafa7404533973c04c513ac619";
    private final Pattern LOGIN_MATCHER = Pattern.compile("[a-z]+");
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final EventService eventService = new EventService();

    public void validateRegistration(User user, String password, String passwordConformation) throws ValidationException {
        validateLogin(user.getLogin());
        if (findUserByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }

        if (Strings.isNullOrEmpty(user.getEmail())) {
            throw new ValidationException("Email is required");
        }
        if (!user.getEmail().matches("[^@]*@[^@]*")) {
            throw new ValidationException("Invalid email");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ValidationException("Email is already in use");
        }

        if (Strings.isNullOrEmpty(password)) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4 characters");
        }
        if (password.length() > 12) {
            throw new ValidationException("Password can't be longer than 12 characters");
        }
        if (!password.equals(passwordConformation)) {
            throw new ValidationException("Confirmation mismatched");
        }
    }

    public User validateEnter(String token, String password) throws ValidationException {
        User user = userRepository.findByLoginOrEmailAndPasswordSha(token, getPasswordSha(password));
        if (user == null) {
            throw new ValidationException("Invalid email/login or password");
        }

        eventService.addEnterEvent(user);

        return user;
    }

    public void validateLogin(String login) throws ValidationException {
        if (Strings.isNullOrEmpty(login)) {
            throw new ValidationException("Login is required");
        }
        if (!LOGIN_MATCHER.matcher(login).matches()) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (login.length() > 8) {
            throw new ValidationException("Login can't be longer than 8 letters");
        }
    }

    public void register(User user, String password) {
        userRepository.save(user, getPasswordSha(password));
    }

    public void logout(User user) {
        eventService.addLogoutEvent(user);
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    public int findCount() {
        return userRepository.findCount();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private String getPasswordSha(String password) {
        return Hashing.sha256().hashBytes((PASSWORD_SALT + password).getBytes(StandardCharsets.UTF_8)).toString();
    }
}
