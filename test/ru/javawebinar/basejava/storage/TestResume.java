package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TestResume {

    public static Resume getResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
       // populateResume(resume);
        return resume;
    }

    private static void populateResume(Resume resume) {
        String cellFirst = "+7 (916) 845-15-32";
        String eMailFirst = "bob@marley.com";
        String skypeFirst = "bobby";

        // creating objective
        TextSection objectiveFirst = new TextSection("singer");

        // creating personalFirst
        TextSection personalFirst = new TextSection("good singer.");

        // creating qualification
        ListSection qualificationFirst = new ListSection();
        List<String> qualificationList = new ArrayList<>();

        // creating experience
        Organization organizationFirstOne = new Organization("Marley", "www.marley.com");
        Employment employmentInFirstOrganization = new Employment("Description", "Position 1", DateUtil.of(2012, Month.JANUARY), DateUtil.of(2015, Month.APRIL));
        Employment employmentInFirstOrganization2 = new Employment("Description 1.1", "Position 1.1", DateUtil.of(2017, Month.FEBRUARY), LocalDate.now());
        Organization organizationSecondOne = new Organization("Bob", "wwww.bob.com");
        Employment employmentInSecondOrganization = new Employment("Description 2", "Psition 2", DateUtil.of(2015, Month.APRIL), DateUtil.of(2017, Month.FEBRUARY));

        List<Employment> firstOrganizationEmploymentList = new ArrayList<>();
        List<Employment> secondOrganizationEmploymentList = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();
        OrganizationSection organizationSection = new OrganizationSection(organizations);

        // creating education
        Organization university = new Organization("NCY", "www.ncy.com");
        Employment study = new Employment("student", "student", DateUtil.of(2007, Month.JANUARY), DateUtil.of(2012, Month.APRIL));
        List<Employment> universitiesEmploymentList = new ArrayList<>();
        List<Organization> universitiesList = new ArrayList<>();
        OrganizationSection educationSection = new OrganizationSection(universitiesList);

        resume.getContacts().put(ContactType.CELL, cellFirst);
        resume.getContacts().put(ContactType.EMAIL, eMailFirst);
        resume.getContacts().put(ContactType.SKYPE, skypeFirst);
        resume.getInformationSections().put(SectionType.OBJECTIVE, objectiveFirst);
        resume.getInformationSections().put(SectionType.PERSONAL, personalFirst);

        qualificationList.add("guitar: bla bla bla");
        qualificationList.add("microphone: la la la");
        qualificationFirst.setDescriptionList(qualificationList);
        resume.getInformationSections().put(SectionType.QUALIFICATIONS, qualificationFirst);

        firstOrganizationEmploymentList.add(employmentInFirstOrganization);
        firstOrganizationEmploymentList.add(employmentInFirstOrganization2);
        secondOrganizationEmploymentList.add(employmentInSecondOrganization);
        organizationFirstOne.setEmploymentsList(firstOrganizationEmploymentList);
        organizationSecondOne.setEmploymentsList(secondOrganizationEmploymentList);
        organizations.add(organizationFirstOne);
        organizations.add(organizationSecondOne);
        resume.getInformationSections().put(SectionType.EXPERIENCE, organizationSection);

        universitiesEmploymentList.add(study);
        university.setEmploymentsList(universitiesEmploymentList);
        universitiesList.add(university);
        educationSection.getOrganizationsList().addAll(universitiesList);
        resume.getInformationSections().put(SectionType.EDUCATION, educationSection);
    }


}
