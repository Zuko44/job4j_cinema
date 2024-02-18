package ru.job4j.cinema.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.job4j.cinema.model.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public class Sql2oTicketRepository implements TicketRepository {
    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Ticket> findBySession(int sessionId) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM tickets WHERE session_id = :sessionId");
            query.addParameter("sessionId", sessionId);
            return query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetch(Ticket.class);
        }
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        Logger logger = LoggerFactory.getLogger(Ticket.class);
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO "
                            + "tickets (session_id, row_number, place_number, user_id) VALUES "
                            + "(:session, :row, :place, :userId)", true)
                    .addParameter("session", ticket.getSession())
                    .addParameter("row", ticket.getRow())
                    .addParameter("place", ticket.getPlace())
                    .addParameter("userId", ticket.getUserId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            ticket.setId(generatedId);
            return Optional.of(ticket);
        } catch (Sql2oException e) {
            logger.error("Error when saving ticket. The ticket is not saved.", e);
        }
        return Optional.empty();
    }
}
