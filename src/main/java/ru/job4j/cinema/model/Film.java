package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class Film {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "description", "description",
            "year", "year",
            "genre_id", "genre",
            "minimal_age", "minimalAge",
            "duration_in_minutes", "duration",
            "file_name", "fileName",
            "realized", "realized"
    );
    private int id;
    private String name;
    private String description;
    private int year;
    private int genre;
    private int minimalAge;
    private int duration;
    private String fileName;
    private boolean realized;

    public Film() {
    }

    @SuppressWarnings("checkstyle:methodlength")
    public Film(int id, String name, String description, int year, int genre, int minimalAge, int duration,
                String fileName, boolean realized) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.genre = genre;
        this.minimalAge = minimalAge;
        this.duration = duration;
        this.fileName = fileName;
        this.realized = realized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Film film = (Film) o;
        return id == film.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isRealized() {
        return realized;
    }

    public void setRealized(boolean realized) {
        this.realized = realized;
    }
}