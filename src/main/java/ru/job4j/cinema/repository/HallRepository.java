package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Hall;

import java.util.Collection;
import java.util.Optional;

public interface HallRepository {
    Collection<Hall> findAll();

    Optional<Hall> findById(int id);
}
