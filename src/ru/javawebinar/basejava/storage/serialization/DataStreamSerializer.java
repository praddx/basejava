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

                switch (sectionName) {
                    case "PERSONAL":
                        dos.writeUTF(((TextSection) section.getValue()).getText());
                        break;
                    case "OBJECTIVE":
                        dos.writeUTF(((TextSection) section.getValue()).getText());
                        break;
                    case "ACHIEVEMENT":
                        writeListSection(dos, ((ListSection) section.getValue()).getDescriptionList());
                        break;
                    case "QUALIFICATIONS":
                        writeListSection(dos, ((ListSection) section.getValue()).getDescriptionList());
                        break;
                    case "EXPERIENCE":
                        writeOrganizationSection(dos, ((OrganizationSection) section.getValue()));
                        break;
                    case "EDUCATION":
                        writeOrganizationSection(dos, ((OrganizationSection) section.getValue()));
                        break;
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

                switch (sectionType) {
                    case PERSONAL:
                        resume.getInformationSections().put(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case OBJECTIVE:
                        resume.getInformationSections().put(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                        resume.getInformationSections().put(sectionType, readListSection(dis));
                        break;
                    case QUALIFICATIONS:
                        resume.getInformationSections().put(sectionType, readListSection(dis));
                        break;
                    case EXPERIENCE:
                        resume.getInformationSections().put(sectionType, readOrganizationSection(dis));
                        break;
                    case EDUCATION:
                        resume.getInformationSections().put(sectionType, readOrganizationSection(dis));
                        break;
                }
            }
            return resume;
        }

    }


    private void writeListSection(DataOutputStream dos, List<String> descriptionList) throws IOException {
        dos.writeInt(descriptionList.size());
        for (int i = 0; i < descriptionList.size(); i++) {
            dos.writeUTF(descriptionList.get(i));
        }
    }

    private ListSection readListSection(DataInputStream dis) throws IOException {
        ListSection listSection = new ListSection();
        int listEntriesCount = dis.readInt();
        for (int i = 0; i < listEntriesCount; i++) {
            listSection.getDescriptionList().add(dis.readUTF());
        }
        return listSection;
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

    private OrganizationSection readOrganizationSection(DataInputStream dis) throws IOException {
        OrganizationSection result = new OrganizationSection();
        int organizationsCount = dis.readInt();
        for (int i = 0; i < organizationsCount; i++) {
            result.getOrganizationsList().add(readOrganization(dis));
        }
        return result;
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
