package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private Link homePage;
    private List<Employment> employmentsList = new ArrayList<>();

    public Organization() {

    }

    public Organization(String name, String url) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(url, "url must not be null");
        this.homePage = new Link(name,url);
    }

    public Organization(String name, String url, List<Employment> employment) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(url, "url must not be null");
        Objects.requireNonNull(employment, "employmentsList info must not be null");
        this.homePage = new Link(name, url);
        this.employmentsList = employment;
    }

    public void setHomePage(Link homePage) {
        this.homePage = homePage;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Employment> getEmploymentsList() {
        return employmentsList;
    }

    public void setEmploymentsList(List<Employment> employmentsList) {
        this.employmentsList = employmentsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(employmentsList, that.employmentsList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(homePage, employmentsList);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", employmentsList=" + employmentsList +
                '}';
    }
}
