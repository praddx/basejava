package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ResumeFour {

    private static final String UUID_4 = "uuid4";
    private static final String fullName_4 = "John Lennon";
    private static final Resume r4 = new Resume(UUID_4, fullName_4);

    //==================== first resume ============================
    // creating contacts
    private static final String cellFirst = "+7 (916) 845-15-32";
    private static final String eMailFirst = "bob@marley.com";
    private static final String skypeFirst = "bobby";

    // creating objective
    private static final TextSection objectiveFirst = new TextSection("singer");

    // creating personalFirst
    private static final TextSection personalFirst = new TextSection("good singer.");

    // creating qualification
    private static final ListSection qualificationFirst = new ListSection();
    private static final List<String> qualificationList = new ArrayList<>();

    // creating experience
    private static final Organization organizationFirstOne = new Organization("Marley", "www.marley.com");
    private static final Employment employmentInFirstOrganization = new Employment("Description", "Position 1", DateUtil.of(2012, Month.JANUARY), DateUtil.of(2015, Month.APRIL));
    private static final Employment employmentInFirstOrganization2 = new Employment("Description 1.1", "Position 1.1", DateUtil.of(2017, Month.FEBRUARY), LocalDate.now());
    private static final Organization organizationSecondOne = new Organization("Bob", "wwww.bob.com");
    private static final Employment employmentInSecondOrganization = new Employment("Description 2", "Psition 2", DateUtil.of(2015, Month.APRIL), DateUtil.of(2017, Month.FEBRUARY));

    private static final List<Employment> firstOrganizationEmploymentList = new ArrayList<>();
    private static final List<Employment> secondOrganizationEmploymentList = new ArrayList<>();
    private static final List<Organization> organizations = new ArrayList<>();
    private static final OrganizationSection organizationSection = new OrganizationSection(organizations);

    // creating education
    private static final Organization university = new Organization("NCY", "www.ncy.com");
    private static final Employment study = new Employment("student", "student", DateUtil.of(2007, Month.JANUARY), DateUtil.of(2012, Month.APRIL));
    private static final List<Employment> universitiesEmploymentList = new ArrayList<>();
    private static final List<Organization> universitiesList = new ArrayList<>();
    private static final OrganizationSection educationSection = new OrganizationSection(universitiesList);

    static {
        r4.getContacts().put(ContactType.CELL, cellFirst);
        r4.getContacts().put(ContactType.EMAIL, eMailFirst);
        r4.getContacts().put(ContactType.SKYPE, skypeFirst);
        r4.getInformationSections().put(SectionType.OBJECTIVE, objectiveFirst);
        r4.getInformationSections().put(SectionType.PERSONAL, personalFirst);

        qualificationList.add("guitar: bla bla bla");
        qualificationList.add("microphone: la la la");
        qualificationFirst.setDescriptionList(qualificationList);
        r4.getInformationSections().put(SectionType.QUALIFICATIONS, qualificationFirst);

        firstOrganizationEmploymentList.add(employmentInFirstOrganization);
        firstOrganizationEmploymentList.add(employmentInFirstOrganization2);
        secondOrganizationEmploymentList.add(employmentInSecondOrganization);
        organizationFirstOne.setEmployment(firstOrganizationEmploymentList);
        organizationSecondOne.setEmployment(secondOrganizationEmploymentList);
        organizations.add(organizationFirstOne);
        organizations.add(organizationSecondOne);
        r4.getInformationSections().put(SectionType.EXPERIENCE, organizationSection);

        universitiesEmploymentList.add(study);
        university.setEmployment(universitiesEmploymentList);
        universitiesList.add(university);
        r4.getInformationSections().put(SectionType.EDUCATION, educationSection);
    }

    public static Resume getResume() {
        return r4;
    }
}
