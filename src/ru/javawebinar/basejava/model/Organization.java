package ru.javawebinar.basejava.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private Map<EmploymentDates, Employment> employmentDates = new HashMap<>();

    public Organization(String name, String url) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(url, "url must not be null");
        this.homePage = new Link(name,url);
    }

    public Organization(String name, String url, Map<EmploymentDates, Employment> employmentDates) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(url, "url must not be null");
        Objects.requireNonNull(employmentDates, "employment info must not be null");
        this.homePage = new Link(name, url);
        this.employmentDates = employmentDates;
    }

    public Link getHomePage() {
        return homePage;
    }

    public Map<EmploymentDates, Employment> getEmploymentDates() {
        return employmentDates;
    }

    public void setEmploymentDates(Map<EmploymentDates, Employment> employmentDates) {
        this.employmentDates = employmentDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(employmentDates, that.employmentDates);
    }

    @Override
    public int hashCode() {

        return Objects.hash(homePage, employmentDates);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", employmentDates=" + employmentDates +
                '}';
    }
}
