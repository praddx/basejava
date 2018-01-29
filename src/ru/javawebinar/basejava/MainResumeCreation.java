package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainResumeCreation {

    public static void main(String[] args) {
        Resume resume = new Resume("Superman");

        Contact cellPhone = new Contact(ContactType.CELL, "+7 (926) 354-87-42");
        Contact eMail = new Contact(ContactType.EMAIL, "superman@gotham.com");

        resume.getContacts().put(cellPhone.getContactType(), cellPhone);
        resume.getContacts().put(eMail.getContactType(), eMail);

        Section personal = new TextSection(SectionType.PERSONAL, "Klark Kent");
        List<String> achievmentsList = new ArrayList<>();
        achievmentsList.add("1. Bla");
        achievmentsList.add("2. BlaBla");
        achievmentsList.add("3. BlaBlaBla");
        Section achievments = new ListSection(SectionType.ACHIEVEMENT, achievmentsList);

        Company univercity = new Company("GIT", "Student", "01/09/1985 - 01.06.1990",
                "bla bla bla");
        List<Company> companiesList = new ArrayList<>();
        companiesList.add(univercity);
        Section companies = new CompanySection(SectionType.EXPERIENCE, companiesList);

        resume.getInformationSections().put(SectionType.PERSONAL, personal);
        resume.getInformationSections().put(SectionType.ACHIEVEMENT, achievments);
        resume.getInformationSections().put(SectionType.EXPERIENCE, companies);

        System.out.println(resume.getFullName());

        for (Map.Entry<ContactType, Contact> contact : resume.getContacts().entrySet()) {
            System.out.println(contact.getValue());
        }

        for (Map.Entry<SectionType, Section> section : resume.getInformationSections().entrySet()) {
            System.out.println(section.getValue());
        }
    }
}
