package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Session;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Repository
public class Sql2oSessionRepository implements SessionRepository {
    private final Sql2o sql2o;

    public Sql2oSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<Session> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions");
            return query.setColumnMappings(Session.COLUMN_MAPPING).executeAndFetch(Session.class);
        }
    }

    @Override
    public List<Session> findByDate(int sessionDate) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions WHERE EXTRACT(YEAR FROM start_time)"
                            + " = :year AND EXTRACT(MONTH FROM start_time) = :month AND EXTRACT(DAY FROM start_time) = :day")
                    .addParameter("year", parseInt(Integer.toString(sessionDate).substring(0, 4)))
                    .addParameter("month", parseInt(Integer.toString(sessionDate).substring(4, 6)))
                    .addParameter("day", parseInt(Integer.toString(sessionDate).substring(6, 8)));
            return query.setColumnMappings(Session.COLUMN_MAPPING).executeAndFetch(Session.class);
        }
    }

    @Override
    public Optional<Session> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions WHERE id = :id");
            query.addParameter("id", id);
            var session = query.setColumnMappings(Session.COLUMN_MAPPING).executeAndFetchFirst(Session.class);
            return Optional.ofNullable(session);
        }
    }
}
