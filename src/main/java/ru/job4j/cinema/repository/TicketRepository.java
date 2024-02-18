package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    List<Ticket> findBySession(int sessionId);

    Optional<Ticket> save(Ticket ticket);
}
