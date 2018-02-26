package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private static final long serialVersionUID = 1L;

    private List<String> descriptionList;

    public List<String> getDescriptionList() {
        return descriptionList;
    }

    public void setDescriptionList(List<String> descriptionList) {
        Objects.requireNonNull(descriptionList, "description must not be null");
        this.descriptionList = descriptionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(descriptionList, that.descriptionList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(descriptionList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        descriptionList.forEach(description -> {
            builder.append(description + "\n");
        });
        return "List section:\n" + builder.toString();
    }
}
