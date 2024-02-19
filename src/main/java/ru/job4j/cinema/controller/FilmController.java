package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.GenreService;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    private final GenreService genreService;

    public FilmController(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("films", filmService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "films/list";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var filmOptional = filmService.findById(id);
        if (filmOptional.isEmpty()) {
            model.addAttribute("message", "Ни одного фильма не найдено!");
            return "errors/404";
        }
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("film", filmOptional.get());
        return "films/one";
    }
}
