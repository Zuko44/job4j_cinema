package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
public class HallService implements HallRepository {
    private final HallRepository hallRepository;

    public HallService(@Lazy HallRepository sql2oHallRepository) {
        this.hallRepository = sql2oHallRepository;
    }

    @Override
    public Collection<Hall> findAll() {
        return hallRepository.findAll();
    }

    @Override
    public Optional<Hall> findById(int id) {
        return hallRepository.findById(id);
    }
}
