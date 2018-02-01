package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends Section {

    private List<String> descriptionList;

    public List<String> getDescriptionList() {
        return descriptionList;
    }

    public void setDescriptionList(List<String> descriptionList) {
        this.descriptionList = descriptionList;
    }

    public void addDescription(String description) {
        this.descriptionList.add(description);
    }

    public void deleteDescription(String description) {
        this.descriptionList.remove(description);
    }

    public ListSection(List<String> listElements) {
        this.descriptionList = listElements;
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
