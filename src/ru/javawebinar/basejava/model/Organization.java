package ru.javawebinar.basejava.model;

import java.util.*;

public class Organization {
    private final Link homePage;
    private List<Employment> employment = new ArrayList<>();

    public Organization(String name, String url) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(url, "url must not be null");
        this.homePage = new Link(name,url);
    }

    public Organization(String name, String url, List<Employment> employment) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(url, "url must not be null");
        Objects.requireNonNull(employment, "employment info must not be null");
        this.homePage = new Link(name, url);
        this.employment = employment;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Employment> getEmploymentDates() {
        return employment;
    }

    public void setEmployment(List<Employment> employment) {
        this.employment = employment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(employment, that.employment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(homePage, employment);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", employment=" + employment +
                '}';
    }
}
