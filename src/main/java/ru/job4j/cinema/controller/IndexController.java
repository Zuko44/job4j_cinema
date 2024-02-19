package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.GenreService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.SessionService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {
    private final SessionService sessionService;
    private final FilmService filmService;
    private final GenreService genreService;
    private final HallService hallService;

    public IndexController(SessionService sessionService, FilmService filmService, GenreService genreService,
                           HallService hallService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.genreService = genreService;
        this.hallService = hallService;
    }

    @GetMapping({"/index"})
    public String getIndex(Model model) {
        var between = sessionService.findByDate(20240208);
        var sessions = new ArrayList<>(between.stream().filter(session ->
                session.getStartTime().toLocalTime().compareTo(LocalTime.now()) > 0).toList());
        List<Integer> filmsId = between.stream().map(Session::getFilmId).distinct().toList();
        var filmsNum = between.stream().filter(session ->
                session.getStartTime().toLocalTime().compareTo(LocalTime.now()) > 0).map(Session::getFilmId).toList();
        Collection<Film> films = new ArrayList<>();
        for (int film : filmsId) {
            films.add(filmService.findById(film).get());
        }
        if (!sessions.isEmpty()) {
            Collections.sort(sessions);
        }
        model.addAttribute("filmsSessions", sessions);
        model.addAttribute("halls", hallService.findAll());
        model.addAttribute("films", films);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("filmsNum", filmsNum);
        model.addAttribute("notRealized", filmService.findByRealize(false));
        model.addAttribute("timeNow", LocalTime.now());
        return "index";
    }
}
