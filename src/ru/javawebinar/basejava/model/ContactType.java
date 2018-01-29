package ru.javawebinar.basejava.model;

public enum ContactType {
    CELL("Тел.: "),
    SKYPE("Skype: "),
    EMAIL("Почта: "),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    WEBPAGE("Домашняя страница");

    private String title;

    ContactType(String name) {
        this.title = name;
    }

    public String getTitle() {
        return title;
    }
}
