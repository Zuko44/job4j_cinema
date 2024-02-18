package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.repository.SessionRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class SessionService implements SessionRepository {
    private final SessionRepository sessionRepository;

    public SessionService(@Lazy SessionRepository sql2oUserRepository) {
        this.sessionRepository = sql2oUserRepository;
    }

    @Override
    public Collection<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public List<Session> findByDate(int sessionDate) {
        return sessionRepository.findByDate(sessionDate);
    }

    @Override
    public Optional<Session> findById(int id) {
        return sessionRepository.findById(id);
    }
}
