package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pradd on 01.01.2018.
 */
public abstract class AbstractStorageTest {

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String fullName_1 = "Bob Marley";
    private static final String fullName_2 = "Marylin Manson";
    private static final String fullName_3 = "Elvis Presley";
    private static final String fullName_4 = "John Lennon";
    private static final Resume r1 = new Resume(UUID_1, fullName_1);
    private static final Resume r2 = new Resume(UUID_2, fullName_2);
    private static final Resume r3 = new Resume(UUID_3, fullName_3);
    private static final Resume r4 = new Resume(UUID_4, fullName_4);

    //==================== first resume ============================
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
    //creating experience
    private static final Organization organizationFirstOne = new Organization("Marley", "www.marley.com");
    private static final Employment employmentInFirstOrganization = new Employment("Position", "Description");
    private static final EmploymentDates employmentDatesFirstOrganization = new EmploymentDates(DateUtil.of(2012, Month.JANUARY), DateUtil.of(2015, Month.APRIL));
    private static final List<Organization> organizationList = new ArrayList<>();
    private static final OrganizationSection organizationSectionFirst = new OrganizationSection(organizationList);

    static {
        r1.getContacts().put(ContactType.CELL, cellFirst);
        r1.getContacts().put(ContactType.EMAIL, eMailFirst);
        r1.getContacts().put(ContactType.SKYPE, skypeFirst);
        r1.getInformationSections().put(SectionType.OBJECTIVE, objectiveFirst);
        r1.getInformationSections().put(SectionType.PERSONAL, personalFirst);
        qualificationList.add("Vehicles: broom, horse, elephant, spaceship");
        qualificationList.add("Weapons: sword, wand, stick, railgun, BFG");
        qualificationFirst.setDescriptionList(qualificationList);
        r1.getInformationSections().put(SectionType.QUALIFICATIONS, qualificationFirst);
        organizationFirstOne.getEmploymentDates().put(employmentDatesFirstOrganization, employmentInFirstOrganization);
        organizationSectionFirst.getOrganizationsList().add(organizationFirstOne);
        r1.getInformationSections().put(SectionType.EXPERIENCE, organizationSectionFirst);
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(r4);
        assertEquals(r4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(r3);
    }

    @Test
    public void update() throws Exception {
        Resume newUuid3 = new Resume(UUID_3, fullName_3);
        storage.update(newUuid3);
        Assert.assertNotSame(r3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(r4);

    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        assertEquals(r1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("xxx");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] allResumes = {r1, r2, r3};
        Resume[] resumesFromStorage = {storage.get(UUID_1), storage.get(UUID_2), storage.get(UUID_3)};
        Assert.assertArrayEquals(allResumes, resumesFromStorage);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(list, Arrays.asList(r1, r3, r2));
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

}