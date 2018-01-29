package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Contact {

    private ContactType contactType;
    private String contact;

    public Contact(ContactType contactType, String contact) {
        this.contactType = contactType;
        this.contact = contact;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact1 = (Contact) o;
        return contactType == contact1.contactType &&
                Objects.equals(contact, contact1.contact);
    }

    @Override
    public int hashCode() {

        return Objects.hash(contactType, contact);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactType=" + contactType +
                ", contact='" + contact + '\'' +
                '}';
    }
}
