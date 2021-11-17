package ru.itmo.wp.model.repository;

import java.util.List;

public interface AbstractRepository<T> {
    int findCount();

    List<T> findAll();

    T save(T element);

    T update(T element);
}
