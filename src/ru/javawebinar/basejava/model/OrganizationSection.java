package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {

    private static final long serialVersionUID = 1L;

    private List<Organization> organizationsList = new ArrayList<>();

    public OrganizationSection(List<Organization> organizationsList) {
        this.organizationsList = organizationsList;
    }

    public List<Organization> getOrganizationsList() {
        return organizationsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizationsList, that.organizationsList);
    }

    @Override
    public int hashCode() {
        return organizationsList.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        organizationsList.forEach(Organization -> {
            builder.append(Organization.toString() + "\n");
        });
        return "Experience: \n" + builder.toString();
    }
}
