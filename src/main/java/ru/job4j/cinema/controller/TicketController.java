package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.SessionService;
import ru.job4j.cinema.service.TicketService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ThreadSafe
@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final SessionService sessionService;
    private final FilmService filmService;
    private final TicketService ticketService;
    private final HallService hallService;

    public TicketController(SessionService sessionService, FilmService filmService, TicketService ticketService,
                            HallService hallService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.ticketService = ticketService;
        this.hallService = hallService;
    }

    @GetMapping("/{sessionId}")
    public String getTicket(Model model, @PathVariable int sessionId) {
        var session = sessionService.findById(sessionId);
        if (session.get().getStartTime().toLocalTime().compareTo(LocalTime.now()) < 1
                && Objects.equals(session.get().getStartTime().toLocalDate(), LocalDate.of(2024, 2, 8))) {
            model.addAttribute("message", "Пожалуйста, обновите"
                    + " предыдущую страницу и повторите попытку снова.");
            return "errors/404";
        }
        var tickets = ticketService.findBySession(sessionId);
        var hall = hallService.findById(session.get().getHallsId()).get();
        List<Integer> foo = new ArrayList<>();
        for (int i = 0; i < tickets.size(); i++) {
            foo.add((tickets.get(i).getRow() - 1) * hall.getPlaceCount() + tickets.get(i).getPlace());
        }
        model.addAttribute("filmSession", session.get());
        model.addAttribute("film", filmService.findById(session.get().getFilmId()).get());
        model.addAttribute("tickets", foo);
        model.addAttribute("halls", hall);
        return "/tickets/one";
    }

    @PostMapping("/by")
    public String byTicket(Model model, @ModelAttribute Ticket ticket) {
        var savedTicket = ticketService.save(ticket);
        if (savedTicket.isEmpty()) {
            model.addAttribute("message", "Пожалуйста, попробуйте выбрать иное место.");
            return "tickets/fault";
        }
        model.addAttribute("ticket", ticket);
        return "tickets/success";
    }
}
