package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainResumeCreation {

    public static void main(String[] args) {
        Resume resume = new Resume("Superman");

        // creating contacts
        String cell = "+7 925 346-85-75";
        String e_mail = "blabla@mail.com";
        String skype = "madmax";
        resume.getContacts().put(ContactType.CELL, cell);
        resume.getContacts().put(ContactType.EMAIL, e_mail);
        resume.getContacts().put(ContactType.SKYPE, skype);

        // creating objective
        TextSection objective = new TextSection("Guardian of the galaxy");
        resume.getInformationSections().put(SectionType.OBJECTIVE, objective);

        // creating personal
        TextSection personal = new TextSection("Smart, fast, strong, agile, kind.");
        resume.getInformationSections().put(SectionType.PERSONAL, personal);

        // creating qualification
        ListSection qualification = new ListSection();
        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("Vehicles: broom, horse, elephant, spaceship");
        qualificationList.add("Weapons: sword, wand, stick, railgun, BFG");
        qualification.setDescriptionList(qualificationList);
        resume.getInformationSections().put(SectionType.QUALIFICATIONS, qualification);

        // creating companies for resume
        List<Company> companies = new ArrayList<>();
        CompanySection companiesSection = new CompanySection();

        String companyName = "League of Justice";
        String position = "Superhero";
        String description = "blablabla";
        LocalDate startDate = LocalDate.of(2015, 2, 15);
        LocalDate endDate = LocalDate.now();
        Company league = new Company(companyName, position, startDate, endDate, description);

        companyName = "Mordor";
        position = "Saruman";
        description = "Evil wizard";
        startDate = LocalDate.of(2013, 6, 20);
        endDate = LocalDate.of(2015, 2, 8);
        Company mordor = new Company(companyName, position, startDate, endDate, description);

        companies.add(league);
        companies.add(mordor);
        companiesSection.setCompaniesList(companies);
        resume.getInformationSections().put(SectionType.EXPERIENCE, companiesSection);

        printResume(resume);
    }

    public static void printResume(Resume resume) {
        System.out.println(resume.getFullName() + "\n");
        resume.getContacts().entrySet().stream()
                .forEachOrdered(c -> {
                    System.out.print(c.getKey().getTitle() + "  ");
                    System.out.println(c.getValue().toString());
                });
        System.out.println();
        resume.getInformationSections().entrySet().stream()
                .forEachOrdered(c -> {
                    System.out.println(c.getKey().getTitle());
                    System.out.println(c.getValue().toString());
                });
    }
}
