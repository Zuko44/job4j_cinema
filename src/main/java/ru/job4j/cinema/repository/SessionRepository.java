package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Session;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    Collection<Session> findAll();

    List<Session> findByDate(int sessionDate);

    Optional<Session> findById(int id);
}
