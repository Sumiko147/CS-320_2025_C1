/*
 * Name:  Sumiko Mitchell
 * Class: CS-320
 * Date:  1/26/2025
 * Description: This JUnit test class verifies the functionality of the ContactService class.
 *               It tests adding, deleting, and updating contact details while ensuring unique IDs.
 */
package com.projectone.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projectone.Contact;            
import com.projectone.ContactService;    

class ContactServiceTest {
    private ContactService contactService;

    // Set up a new ContactService before each test
    @BeforeEach
    void setUp() {
        contactService = new ContactService();
    }

    // Test: Adding a new contact and verifying stored values
    @Test
    void testAddContact() {
        contactService.addContact("Rose", "Stone", "1234567890", "123 Main St, Springfield, IL");

        // Retrieve the first contact (since ID is auto-generated)
        String contactId = contactService.getContactList().get(0).getContactId();
        Contact contact = contactService.getContact(contactId);

        assertNotNull(contact); // Ensure contact is stored
        assertEquals("Rose", contact.getFirstName());
        assertEquals("Stone", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St, Springfield, IL", contact.getAddress());
    }

    // Test: Deleting a contact and ensuring it no longer exists
    @Test
    void testDeleteContact() {
        contactService.addContact("Lily", "Clock", "0987654321", "456 Oak St, Denver, CO 80202");
        String contactId = contactService.getContactList().get(0).getContactId();

        assertTrue(contactService.deleteContact(contactId)); // Ensure deletion was successful
        assertNull(contactService.getContact(contactId)); // Contact should be removed
    }

    // Test: Updating first name of an existing contact
    @Test
    void testUpdateFirstName() {
        contactService.addContact("Daisy", "River", "1112223333", "789 Pine St, Austin, TX 73301");
        String contactId = contactService.getContactList().get(0).getContactId();

        contactService.updateFirstName(contactId, "Ivy");
        assertEquals("Ivy", contactService.getContact(contactId).getFirstName());
    }

    // Test: Updating last name of an existing contact
    @Test
    void testUpdateLastName() {
        contactService.addContact("Tulip", "Chair", "5556667777", "321 Elm St, Miami, FL 33101");
        String contactId = contactService.getContactList().get(0).getContactId();

        contactService.updateLastName(contactId, "Spoon");
        assertEquals("Spoon", contactService.getContact(contactId).getLastName());
    }

    // Test: Updating phone number of an existing contact
    @Test
    void testUpdatePhone() {
        contactService.addContact("Jasmine", "Bridge", "4445556666", "65 Birch St, Seattle, WA 98101");
        String contactId = contactService.getContactList().get(0).getContactId();

        contactService.updatePhone(contactId, "7778889999");
        assertEquals("7778889999", contactService.getContact(contactId).getPhone());
    }

    // Test: Updating address of an existing contact
    @Test
    void testUpdateAddress() {
        contactService.addContact("Orchid", "Cloud", "2223334444", "987 Cedar St, Phoenix, AZ");
        String contactId = contactService.getContactList().get(0).getContactId();

        contactService.updateAddress(contactId, "111 Redwood St, Boston, MA");
        assertEquals("111 Redwood St, Boston, MA", contactService.getContact(contactId).getAddress());
    }

    // Test: Ensuring contact ID is unique and cannot be duplicated
    @Test
    void testUniqueContactId() {
        contactService.addContact("Marigold", "Compass", "9998887777", "555 Oak Ave, Nashville, TN");
        String contactId = contactService.getContactList().get(0).getContactId();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(new Contact(contactId, "Violet", "Lantern", "6665554444", "777 Maple Dr, Chicago, IL"));
        });

        assertEquals("Contact ID must be unique", exception.getMessage());
    }

    // Test: Verify displayContacts() method functionality
    @Test
    void testDisplayContacts() {
        ContactService service = new ContactService();
        
        // Add a few contacts to test display
        service.addContact("Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
        service.addContact("Rose", "Stone", "0987654321", "456 Oak St, Carson, NV 89701");
        
        // Redirect System.out to capture printed output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        
        // Call display method
        service.displayContacts();
        
        // Verify output contains contact information
        String output = outContent.toString();
        assertTrue(output.contains("Logan"));
        assertTrue(output.contains("Pants"));
        assertTrue(output.contains("Rose"));
        assertTrue(output.contains("Stone"));
        
        // Restore normal System.out
        System.setOut(System.out);
    }

}