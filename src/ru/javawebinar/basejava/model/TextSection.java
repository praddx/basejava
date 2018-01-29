package ru.javawebinar.basejava.model;

public class TextSection extends Section {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextSection(SectionType sectionType) {
        super(sectionType);
    }

    public TextSection(SectionType sectionType, String text) {
        super(sectionType);
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "text='" + text + '\'' +
                '}';
    }
}
