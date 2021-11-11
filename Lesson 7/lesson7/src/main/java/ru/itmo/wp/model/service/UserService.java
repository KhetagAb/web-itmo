package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import ru.itmo.wp.model.domain.Article;
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
    private static final Pattern LOGIN_MATCHER = Pattern.compile("[a-z]+");
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final ArticleService articleService = new ArticleService();

    public void register(User user, String password) {
        userRepository.save(user, getPasswordSha(password));
    }

    public User enter(String login, String password) throws ValidationException {
        User user = userRepository.findByLoginAndPasswordSha(login, getPasswordSha(password));
        if (user == null) {
            throw new ValidationException("Invalid login or password");
        }
        return user;
    }

    public void validateRegistration(User user, String password) throws ValidationException {
        validateEnter(user.getLogin(), password);
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }
    }

    public void validateEnter(String login, String password) throws ValidationException {
        if (Strings.isNullOrEmpty(login)) {
            throw new ValidationException("Login is required");
        }
        if (!LOGIN_MATCHER.matcher(login).matches()) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (login.length() > 8) {
            throw new ValidationException("Login can't be longer than 8 letters");
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
    }

    public Article switchArticleVisibility(User user, long articleId) throws ValidationException {
        Article article = articleService.findById(articleId);
        if (article == null || article.getUserId() != user.getId()) {
            throw new ValidationException("User with id " + user.getId() + " don't have access to article " + articleId);
        }
        article = articleService.switchVisibility(article);
        return article;
    }

    public User findById(long id) {
        return userRepository.find(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private String getPasswordSha(String password) {
        return Hashing.sha256().hashBytes((PASSWORD_SALT + password).getBytes(StandardCharsets.UTF_8)).toString();
    }
}
