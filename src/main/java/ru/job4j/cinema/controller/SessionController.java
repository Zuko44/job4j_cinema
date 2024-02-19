package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.GenreService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.SessionService;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ThreadSafe
@Controller
@RequestMapping("/filmSessions")
public class SessionController {
    private final SessionService sessionService;
    private final FilmService filmService;
    private final GenreService genreService;
    private final HallService hallService;

    public SessionController(SessionService sessionService, FilmService filmService,
                             GenreService genreService, HallService hallService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.genreService = genreService;
        this.hallService = hallService;
    }

    @GetMapping("/{sessionDate}")
    public String getByDate(Model model, @PathVariable int sessionDate) {
        var sessions = sessionService.findByDate(sessionDate);
        if (sessions.isEmpty()) {
            model.addAttribute("message", "Активные сессии отсутствуют");
            return "errors/404";
        }
        List<Integer> filmsId = sessions.stream().map(Session::getFilmId).distinct().toList();
        var allFilms = filmService.findAll();
        var films = allFilms.stream().filter(all -> filmsId.contains(all.getId())).collect(Collectors.toList());
        Collections.sort(sessions);
        model.addAttribute("filmsSessions", sessions);
        model.addAttribute("halls", hallService.findAll());
        model.addAttribute("films", films);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("timeNow", LocalTime.now());
        return "filmSessions/one";
    }
}
