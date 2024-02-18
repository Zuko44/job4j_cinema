package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.FilmRepository;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
public class FilmService implements FilmRepository {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository sql2oFilmRepository) {
        this.filmRepository = sql2oFilmRepository;
    }

    @Override
    public Optional<Film> findById(int id) {
        return filmRepository.findById(id);
    }

    @Override
    public Collection<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Collection<Film> findByRealize(boolean realize) {
        return filmRepository.findByRealize(realize);
    }
}
