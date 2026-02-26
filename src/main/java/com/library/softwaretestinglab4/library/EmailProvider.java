package com.library.softwaretestinglab4.library;

public interface EmailProvider {

    // Sends an email to the recipient (Patron or member of the library)
    boolean sendEmail(String recipient, String message);
}
