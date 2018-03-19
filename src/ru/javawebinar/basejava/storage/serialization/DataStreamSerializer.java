package ru.javawebinar.basejava.storage.serialization;

import ru.javawebinar.basejava.model.*;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DataStreamSerializer implements SerializationStrategy {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            dos.writeInt(r.getContacts().size());
            for (Map.Entry<ContactType, String> contact : r.getContacts().entrySet()) {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            }
            dos.writeInt(r.getInformationSections().size());
            for (Map.Entry<SectionType, Section> section : r.getInformationSections().entrySet()) {
                String sectionName = section.getKey().name();
                dos.writeUTF(sectionName);

                if (sectionName.equals("PERSONAL") || sectionName.equals("OBJECTIVE")) {
                    dos.writeUTF(((TextSection) section.getValue()).getText());
                }

                if (sectionName.equals("ACHIEVEMENT") || sectionName.equals("QUALIFICATIONS")) {
                    writeListSection(dos, ((ListSection) section.getValue()).getDescriptionList());
                }

                if (sectionName.equals("EXPERIENCE") || sectionName.equals("EDUCATION")) {
                    writeOrganizationSection(dos, ((OrganizationSection) section.getValue()));
                }
            }

        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            //reading contacts
            int contactsCount = dis.readInt();
            for (int i = 0; i < contactsCount; i++) {
                resume.getContacts().put(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            //reading sections
            int sectionsCount = dis.readInt();
            for (int i = 0; i < sectionsCount; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                    resume.getInformationSections().put(sectionType, new TextSection(dis.readUTF()));
                }

                if (sectionType == SectionType.ACHIEVEMENT || sectionType == SectionType.QUALIFICATIONS) {
                    ListSection listSection = new ListSection();
                    listSection.getDescriptionList().addAll(readListSection(dis));
                    resume.getInformationSections().put(sectionType, listSection);
                }

                if (sectionType == SectionType.EXPERIENCE || sectionType == SectionType.EDUCATION) {
                    OrganizationSection organizationSection = new OrganizationSection();
                    organizationSection.getOrganizationsList().addAll(readOrganizationSection(dis));
                    resume.getInformationSections().put(sectionType, organizationSection);
                }
            }
            return resume;
        }

    }


    private static void writeListSection(DataOutputStream dos, List<String> descriptionList) throws IOException {
        dos.writeInt(descriptionList.size());
        for (int i = 0; i < descriptionList.size(); i++) {
            dos.writeUTF(descriptionList.get(i));
        }
    }

    private static List<String> readListSection(DataInputStream dis) throws IOException {
        List<String> result = new ArrayList<>();
        int listEntriesCount = dis.readInt();
        for (int i = 0; i < listEntriesCount; i++) {
            result.add(dis.readUTF());
        }
        return result;
    }

    private void writeOrganizationSection(DataOutputStream dos, OrganizationSection organizationSection) throws IOException {
        dos.writeInt(organizationSection.getOrganizationsList().size());
        for (int i = 0; i < organizationSection.getOrganizationsList().size(); i++) {
            writeOrganization(dos, organizationSection.getOrganizationsList().get(i));
        }
    }

    private void writeOrganization(DataOutputStream dos, Organization organization) throws IOException {
        writeLink(dos, organization.getHomePage());
        writeEmployments(dos, organization.getEmploymentsList());
    }

    private void writeLink(DataOutputStream dos, Link link) throws IOException {
        dos.writeUTF(link.getName());
        dos.writeUTF(link.getUrl());
    }

    private void writeEmployments(DataOutputStream dos, List<Employment> employments) throws IOException {
        dos.writeInt(employments.size());
        for (int i = 0; i < employments.size(); i++) {
            writeEmployment(dos, employments.get(i));
        }
    }

    private void writeEmployment(DataOutputStream dos, Employment employment) throws IOException {
        dos.writeUTF(employment.getDescription());
        dos.writeUTF(employment.getPosition());
        dos.writeUTF(employment.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dos.writeUTF(employment.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    private List<Organization> readOrganizationSection(DataInputStream dis) throws IOException {
        List<Organization> organizationsList = new ArrayList<>();
        int organizationsCount = dis.readInt();
        for (int i = 0; i < organizationsCount; i++) {
            organizationsList.add(readOrganization(dis));
        }
        return organizationsList;
    }

    private Organization readOrganization(DataInputStream dis) throws IOException {
        Organization organization = new Organization();
        organization.setHomePage(readLink(dis));
        organization.setEmploymentsList(readEmploymentsList(dis));
        return organization;
    }

    private Link readLink(DataInputStream dis) throws IOException {
        Link homePage = new Link();
        homePage.setName(dis.readUTF());
        homePage.setUrl(dis.readUTF());
        return homePage;
    }

    private List<Employment> readEmploymentsList(DataInputStream dis) throws IOException {
        List<Employment> employmentsList = new ArrayList<>();
        int employmentCount = dis.readInt();
        for (int i = 0; i < employmentCount; i++) {
            employmentsList.add(readEmployment(dis));
        }
        return employmentsList;
    }

    private Employment readEmployment(DataInputStream dis) throws IOException {
        Employment employment = new Employment();
        employment.setDescription(dis.readUTF());
        employment.setPosition(dis.readUTF());
        employment.setStartDate(LocalDate.parse(dis.readUTF(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        employment.setStartDate(LocalDate.parse(dis.readUTF(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return employment;
    }
}
