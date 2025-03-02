/*
 * Name:  Sumiko Mitchell 
 * Class: CS-320
 * Date:  1/26/2025; Corrected 2/5/205
 * Description: The ContactService class manages contacts by adding, updating, deleting, and retrieving contact objects.
 */

package com.projectone;

import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private final ArrayList<Contact> contactList; // Keep private to maintain encapsulation

    // Constructor
    public ContactService() {
        this.contactList = new ArrayList<>();
    }

    // Adds a new contact (ID is auto-generated)
    public void addContact(String firstName, String lastName, String phone, String address) {
        Contact newContact = new Contact(firstName, lastName, phone, address);
        addContact(newContact);
    }

    // Adds a contact manually (ensures ID uniqueness)
    public void addContact(Contact newContact) {
        for (Contact contact : contactList) {
            if (contact.getContactId().equals(newContact.getContactId())) {
                throw new IllegalArgumentException("Contact ID must be unique");
            }
        }
        contactList.add(newContact);
    }

    // Public getter to retrieve a **copy** of contactList (prevents direct modification)
    public List<Contact> getContactList() {
        return new ArrayList<>(contactList);
    }

    // Retrieves a contact by ID
    public Contact getContact(String contactId) {
        return findContact(contactId);
    }

    // Updates first name
    public void updateFirstName(String contactId, String newFirstName) {
        Contact contact = findContact(contactId);
        contact.setFirstName(newFirstName);
    }

    // Updates last name
    public void updateLastName(String contactId, String newLastName) {
        Contact contact = findContact(contactId);
        contact.setLastName(newLastName);
    }

    // Updates phone number
    public void updatePhone(String contactId, String newPhone) {
        Contact contact = findContact(contactId);
        contact.setPhone(newPhone);
    }

    // Updates address
    public void updateAddress(String contactId, String newAddress) {
        Contact contact = findContact(contactId);
        contact.setAddress(newAddress);
    }

    // Deletes a contact by ID
    public boolean deleteContact(String contactId) {
        Contact contact = findContact(contactId);
        return contactList.remove(contact);
    }

    // Finds a contact by ID
    private Contact findContact(String contactId) {
        for (Contact contact : contactList) {
            if (contact.getContactId().equals(contactId)) {
                return contact;
            }
        }
        // Returning null here enables JUnit testing; in production, throw an exception. 
        // throw new IllegalArgumentException("Contact with ID " + contactId + " not found");
        return null;
    }

    // Displays all contacts (for debugging/testing)
    public void displayContacts() {
        for (Contact contact : contactList) {
            System.out.println("ID: " + contact.getContactId() + ", Name: " + contact.getFirstName() + " " + contact.getLastName());
        }
    }
}
