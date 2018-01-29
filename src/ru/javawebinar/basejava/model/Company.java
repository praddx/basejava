package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Company {

    private String companyName;
    private String position;
    private String dates;
    private String description;

    public Company(String companyName, String position, String dates, String description) {
        this.companyName = companyName;
        this.position = position;
        this.dates = dates;
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

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
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
                Objects.equals(dates, company.dates) &&
                Objects.equals(description, company.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(companyName, position, dates, description);
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", dates='" + dates + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
