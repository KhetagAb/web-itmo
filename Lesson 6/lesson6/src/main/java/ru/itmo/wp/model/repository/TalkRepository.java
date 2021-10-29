package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Talk;

import java.util.List;

public interface TalkRepository extends AbstractRepository<Talk> {
    Talk findById(long id);

    List<Talk> findAllRelatedByUserId(long id);
}
