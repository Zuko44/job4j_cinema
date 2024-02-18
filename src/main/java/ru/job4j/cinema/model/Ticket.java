package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class Ticket {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "session_id", "session",
            "row_number", "row",
            "place_number", "place",
            "user_id", "userId"
    );
    private int id;
    private int session;
    private int row;
    private int place;
    private int userId;

    public Ticket(int id, int session, int row, int place, int userId) {
        this.id = id;
        this.session = session;
        this.row = row;
        this.place = place;
        this.userId = userId;
    }

    public Ticket() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return id == ticket.id && session == ticket.session && row == ticket.row && place == ticket.place
                && userId == ticket.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, session, row, place, userId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
