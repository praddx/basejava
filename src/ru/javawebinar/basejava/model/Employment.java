package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LoacaDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Employment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;
    private String position;
    @XmlJavaTypeAdapter(LoacaDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LoacaDateAdapter.class)
    private LocalDate endDate;

    public Employment() {

    }

    public Employment(String description, String position, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(position, "position must not be null");
        Objects.requireNonNull(startDate, "start day must not be null");
        Objects.requireNonNull(endDate, "end day must not be null");
        this.description = description;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
                Objects.equals(position, that.position) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(description, position, startDate, endDate);
    }
}
