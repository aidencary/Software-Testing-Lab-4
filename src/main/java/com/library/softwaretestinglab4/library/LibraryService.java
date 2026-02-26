package com.library.softwaretestinglab4.library;

import com.library.softwaretestinglab4.library.Exceptions.DatabaseUpdateException;
import com.library.softwaretestinglab4.library.Exceptions.EmailDeliveryException;
import com.library.softwaretestinglab4.library.Exceptions.InvalidInputException;
import com.library.softwaretestinglab4.library.Exceptions.ResourceUnavailableException;

import java.util.UUID;

public class LibraryService {

    private final EmailProvider emailProvider;
    private final ResourceRepository resourceRepository;

    public LibraryService(EmailProvider emailProvider, ResourceRepository resourceRepository) {
        this.emailProvider = emailProvider;
        this.resourceRepository = resourceRepository;
    }

    public boolean checkoutResource(UUID resourceId, String memberEmail) {
        // ID validation is simpler because UUID cannot be "empty" like a String
        if (resourceId == null || memberEmail == null || memberEmail.isEmpty()) {
            throw new InvalidInputException("Resource ID and Member Email must not be null");
        }

        // Check availability
        if (!resourceRepository.isResourceAvailable(resourceId)) {
            throw new ResourceUnavailableException("Resource " + resourceId + " is already checked out");
        }

        // Update status
        boolean updated = resourceRepository.updateStatus(resourceId, false);
        if (!updated) {
            throw new DatabaseUpdateException("Failed to update resource status in database");
        }

        // Send email
        String content = "Resource ID: " + resourceId + " checked out.";
        boolean emailSent = emailProvider.sendEmail(memberEmail, content);
        if (!emailSent) {
            throw new EmailDeliveryException("Checkout successful, but notification email failed to send");
        }

        return true;
    }

}
