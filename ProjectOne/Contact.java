/*
 * Name:  Sumiko Mitchell 
 * Class: CS-320
 * Date:  1/26/2025; Corrected 2/5/2025
 * Description: The Contact class represents a single contact in the contact management system.
*/

package com.projectone;

import java.util.Random;

public class Contact {
    // Immutable Contact ID, assigned once upon object creation
    private final String contactId;
	
	// Static random instance for ID generation
	private static final Random random = new Random();
    
    // Updatable fields: firstName, lastName, phone, and address
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /*
     * Constructor for Contact object -- Auto-generated ID
     * firstName  First name (max 10 characters, non-null)
     * lastName   Last name (max 10 characters, non-null)
     * phone      Phone number (exactly 10 digits, non-null)
     * address    Address (max 30 characters, non-null)
     * 
     * IllegalArgumentException if any parameter fails validation
    */
    public Contact(String firstName, String lastName, String phone, String address){
		this(generateUniqueId(), firstName, lastName, phone, address);
	}

	/*
	 * Constructor for Contact object -- Specific ID
     * contactId  Unique ID (max 10 characters, non-null, immutable)
	 * <all other same>
	*/
	 
    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        // Validate all input fields
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Invalid Contact ID");
        }
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid First Name");
        }
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid Last Name");
        }
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid Phone Number");
        }
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid Address");
        }

        // Assign values after validation
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }
	
	// Generate a unique ID using Random
	private static String generateUniqueId(){
		return String.valueOf(1000000000 + random.nextInt(900000000));
	}

    // Getter for contactId
    public String getContactId() {
        return contactId;
    }

    //Getter for firstName
    public String getFirstName() {
        return firstName;
    }

    // Getter for lastName
    public String getLastName() {
        return lastName;
    }

    //Getter for phone number
    public String getPhone() {
        return phone;
    }

    //Getter for address
    public String getAddress() {
        return address;
    }

    //Updates the firstName field
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid First Name");
        }
        this.firstName = firstName;
    }

    // Updates the lastName field 
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid Last Name");
        }
        this.lastName = lastName;
    }

    // Updates the phone number
    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid Phone Number");
        }
        this.phone = phone;
    }

    // Updates the address field (max 30 characters, non-null).
    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid Address");
        }
        this.address = address;
    }
}
