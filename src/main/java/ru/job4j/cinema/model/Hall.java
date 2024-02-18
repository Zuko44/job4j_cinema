package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class Hall {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "row_count", "rowCount",
            "place_count", "placeCount",
            "name", "name"
    );
    private int id;
    private int rowCount;
    private int placeCount;
    private String name;

    public Hall(int id, int rowCount, int placeCount, String name) {
        this.id = id;
        this.rowCount = rowCount;
        this.placeCount = placeCount;
        this.name = name;
    }

    public Hall() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hall hall = (Hall) o;
        return id == hall.id && rowCount == hall.rowCount && placeCount == hall.placeCount && name.equals(hall.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rowCount, placeCount, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
