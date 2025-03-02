/*
 * Name:  Sumiko Mitchell 
 * Class: CS-320
 * Date:  1/26/2025
 * Description: JUnit test class for the Contact class. Ensures proper validation, immutability of contactId, 
 *              and correct behavior of setters.
 */
package com.projectone.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Modifier; // Importing Modifier to check if the contactId field is final (immutable)

import com.projectone.Contact;

class ContactTest {

    // Test valid contact creation
    @Test
    void testValidContactCreation() {
        Contact contact = new Contact("1234567890", "Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
        
        assertEquals("1234567890", contact.getContactId(), "Contact ID mismatch");
        assertEquals("Logan", contact.getFirstName(), "First name mismatch");
        assertEquals("Pants", contact.getLastName(), "Last name mismatch");
        assertEquals("1234567890", contact.getPhone(), "Phone number mismatch");
        assertEquals("123 Main St, Carson, NV 89701", contact.getAddress(), "Address mismatch");
    }

    // Test for null and too-long Contact ID
    @Test
    void testInvalidContactId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701"));
        assertEquals("Invalid Contact ID", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("12345678901", "Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701"));
        assertEquals("Invalid Contact ID", exception.getMessage());
    }

    // Test for invalid first name (null and too long)
    @Test
    void testInvalidFirstName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("1234567890", null, "Pants", "1234567890", "123 Main St, Carson, NV 89701"));
        assertEquals("Invalid First Name", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("1234567890", "LoganLogan12", "Pants", "1234567890", "123 Main St, Carson, NV 89701"));
        assertEquals("Invalid First Name", exception.getMessage());
    }

    // Test for invalid last name (null and too long)
    @Test
    void testInvalidLastName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("1234567890", "Logan", null, "1234567890", "123 Main St, Carson, NV 89701"));
        assertEquals("Invalid Last Name", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("1234567890", "Logan", "PantsPantsPants", "1234567890", "123 Main St, Carson, NV 89701"));
        assertEquals("Invalid Last Name", exception.getMessage());
    }

    // Test for invalid phone numbers (too short, too long, non-numeric)
    @Test
    void testInvalidPhoneNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("1234567890", "Logan", "Pants", "1234", "123 Main St, Carson, NV 89701"));
        assertEquals("Invalid Phone Number", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("1234567890", "Logan", "Pants", "1234567890123", "123 Main St, Carson, NV 89701"));
        assertEquals("Invalid Phone Number", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("1234567890", "Logan", "Pants", "123ABC7890", "123 Main St, Carson, NV 89701"));
        assertEquals("Invalid Phone Number", exception.getMessage());
    }

    // Test for invalid address (null and too long)
    @Test
    void testInvalidAddress() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("1234567890", "Logan", "Pants", "1234567890", null));
        assertEquals("Invalid Address", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
            new Contact("1234567890", "Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701 9876543210987"));
        assertEquals("Invalid Address", exception.getMessage());
    }

    // Test contact ID immutability using reflection
    @Test
    void testImmutableContactId() throws NoSuchFieldException {
        assertTrue(Modifier.isFinal(Contact.class.getDeclaredField("contactId").getModifiers()), "Contact ID should be final");
    }

    // Test updating first name
    @Test
    void testUpdatingFirstName() {
        Contact contact = new Contact("1234567890", "Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName(), "First name update failed");
    }

    // Test updating last name
    @Test
    void testUpdatingLastName() {
        Contact contact = new Contact("1234567890", "Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName(), "Last name update failed");
    }

    // Test updating phone number
    @Test
    void testUpdatingPhone() {
        Contact contact = new Contact("1234567890", "Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
        contact.setPhone("0987654321");
        assertEquals("0987654321", contact.getPhone(), "Phone number update failed");
    }

    // Test updating address
    @Test
    void testUpdatingAddress() {
        Contact contact = new Contact("1234567890", "Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
        contact.setAddress("456 Elm St, Carson, NV 89701");
        assertEquals("456 Elm St, Carson, NV 89701", contact.getAddress(), "Address update failed");
    }
    
 // 2025/02/16 SM: Additional tests to increase coverage results (from 69.2%)
 // Test verifying contact creation using auto-generated ID constructor
	 @Test
	 void testAutoGeneratedIdConstructor() {
	     // Create contact using the constructor that auto-generates ID
	     Contact contact = new Contact("Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
	     
	     // Verify the auto-generated ID meets requirements
	     assertNotNull(contact.getContactId());
	     assertTrue(contact.getContactId().length() <= 10);
	     assertTrue(contact.getContactId().matches("\\d+")); // Verify it's numeric
	 }
	
	 // Test ensuring multiple contacts get unique auto-generated IDs
	 @Test
	 void testMultipleAutoGeneratedIds() {
	     // Create two contacts using auto-generated IDs
	     Contact contact1 = new Contact("Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
	     Contact contact2 = new Contact("Rose", "Stone", "0987654321", "456 Oak St, Carson, NV 89701");
	     
	     // Verify the two contacts received different IDs
	     assertNotEquals(contact1.getContactId(), contact2.getContactId());
	 }
	
	 // Test verifying proper handling of whitespace in contact fields
	 @Test
	 void testWhitespaceHandling() {
	     // Create contact with extra whitespace in various fields
	     Contact contact = new Contact("1234567890", "Logan  ", "Pants  ", "1234567890", "  123 Main St, Carson, NV  ");
	     
	     // Verify whitespace is preserved in all fields
	     assertEquals("Logan  ", contact.getFirstName());
	     assertEquals("Pants  ", contact.getLastName());
	     assertEquals("  123 Main St, Carson, NV  ", contact.getAddress());
	 }
	
	 // Test verifying behavior with maximum length values for all fields
	 @Test
	 void testBoundaryConditions() {
	     // Create contact with maximum length values for all fields
	     Contact contact = new Contact(
	                                 "LoganLogan", // 10 chars
	                                 "PantsPants", // 10 chars
	                                 "1234567890", // 10 digits
	                                 "1234 Main St, Carson, NV 89701"); // 30 chars
	     
	     // Verify contact creation succeeded
	     assertNotNull(contact);
	     
	     // Test updating all fields with new maximum length values
	     contact.setFirstName("LoganLogan"); // 10 chars
	     contact.setLastName("PantsPants"); // 10 chars
	     contact.setPhone("9876543210"); // 10 digits
	     contact.setAddress("45678 Oak St, Carson, NV 89701"); // 30 chars
	 }  
	 
	// 2025/02/16 SM: Additional tests to increase coverage results (from 75.8%)
	// Test invalid updates to first name
	@Test
	void testInvalidFirstNameUpdate() {
	    Contact contact = new Contact("Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
	    
	    // Test null first name update
	    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
	        contact.setFirstName(null));
	    assertEquals("Invalid First Name", exception.getMessage());
	    
	    // Test too long first name update
	    exception = assertThrows(IllegalArgumentException.class, () ->
	        contact.setFirstName("LoganLoganLogan"));
	    assertEquals("Invalid First Name", exception.getMessage());
	}

	// Test invalid updates to last name
	@Test
	void testInvalidLastNameUpdate() {
	    Contact contact = new Contact("Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
	    
	    // Test null last name update
	    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
	        contact.setLastName(null));
	    assertEquals("Invalid Last Name", exception.getMessage());
	    
	    // Test too long last name update
	    exception = assertThrows(IllegalArgumentException.class, () ->
	        contact.setLastName("PantsPantsPants"));
	    assertEquals("Invalid Last Name", exception.getMessage());
	}

	// Test invalid updates to phone
	@Test
	void testInvalidPhoneUpdate() {
	    Contact contact = new Contact("Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
	    
	    // Test null phone update
	    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
	        contact.setPhone(null));
	    assertEquals("Invalid Phone Number", exception.getMessage());
	    
	    // Test non-numeric phone update
	    exception = assertThrows(IllegalArgumentException.class, () ->
	        contact.setPhone("123ABC4567"));
	    assertEquals("Invalid Phone Number", exception.getMessage());
	}

	// Test invalid updates to address
	@Test
	void testInvalidAddressUpdate() {
	    Contact contact = new Contact("Logan", "Pants", "1234567890", "123 Main St, Carson, NV 89701");
	    
	    // Test null address update
	    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
	        contact.setAddress(null));
	    assertEquals("Invalid Address", exception.getMessage());
	    
	    // Test too long address update
	    exception = assertThrows(IllegalArgumentException.class, () ->
	        contact.setAddress("123 Main St, Carson, NV 89701 Extra Long Address That Exceeds Thirty Characters"));
	    assertEquals("Invalid Address", exception.getMessage());
	}	 
	
}
