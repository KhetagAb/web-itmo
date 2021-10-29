package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

public interface UserRepository extends AbstractRepository<User> {
    User findById(long id);

    User findByLogin(String login);

    User findByEmail(String email);

    User findByLoginOrEmailAndPasswordSha(String login, String passwordSha);

    User save(User user, String passwordSha);
}
