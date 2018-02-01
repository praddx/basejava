package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Company {

    private String companyName;
    private String position;
    private String description;
    LocalDate startDate;
    LocalDate endDate;

    public Company(String companyName, String position, LocalDate startDate, LocalDate endDate, String description) {
        this.companyName = companyName;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyName, company.companyName) &&
                Objects.equals(position, company.position) &&
                Objects.equals(description, company.description) &&
                Objects.equals(startDate, company.startDate) &&
                Objects.equals(endDate, company.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(companyName, position, description, startDate, endDate);
    }

    @Override
    public String toString() {
        return "companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", dates=\'" + startDate.toString() + " - " + endDate.toString() + '\'' +
                ", description='" + description + '\'';
    }
}
