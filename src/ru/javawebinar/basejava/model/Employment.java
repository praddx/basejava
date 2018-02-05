package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Employment {

    private String description;
    private String position;

    public Employment(String description, String position) {
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(position, "position must not be null");
        this.description = description;
        this.position = position;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", position, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employment that = (Employment) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {

        return Objects.hash(description, position);
    }
}
