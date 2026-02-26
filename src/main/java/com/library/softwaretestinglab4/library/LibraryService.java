package com.baarsch_bytes.Library;

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
        if (resourceId == null) return false;

        if (!resourceRepository.isResourceAvailable(resourceId)) {
            return false;
        }

        return resourceRepository.updateStatus(resourceId, false) &&
            emailProvider.sendEmail(memberEmail, "Resource ID: " + resourceId + " checked out.");
    }

}
