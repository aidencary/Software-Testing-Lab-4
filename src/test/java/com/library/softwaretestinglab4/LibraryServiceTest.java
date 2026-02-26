package com.library.softwaretestinglab4;

import com.library.softwaretestinglab4.library.EmailProvider;
import com.library.softwaretestinglab4.library.LibraryService;
import com.library.softwaretestinglab4.library.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    // Create mocks
    @Mock
    private EmailProvider mockEmailProvider;

    @Mock
    private ResourceRepository mockResourceRepository;

    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        libraryService = new LibraryService(mockEmailProvider, mockResourceRepository);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/libraryTestCases.csv", numLinesToSkip = 1)
    public void testCheckoutResource(String testCaseId, String resourceIdRaw,
                                     String memberEmail, boolean isAvailable, boolean updateStatus,
                                     boolean sendEmail, boolean expectedResult, boolean isError) {

        // Manually handle the "null" string from CSV
        UUID resourceId = (resourceIdRaw == null || resourceIdRaw.equalsIgnoreCase("null"))
                ? null
                : UUID.fromString(resourceIdRaw);

        // If the resource is null, it returns false
        if (resourceId != null) {
            // Check if the resource is available
            when(mockResourceRepository.isResourceAvailable(resourceId)).thenReturn(isAvailable);

            // If the resource is available, check it out by setting its availability status to false
            if (isAvailable) {
                when(mockResourceRepository.updateStatus(resourceId, false)).thenReturn(updateStatus);

                // Send the confirmation email that the resource has been checked out
                if (updateStatus) {
                    String expectedEmailContent = "Resource ID: " + resourceId + " checked out.";
                    when(mockEmailProvider.sendEmail(memberEmail, expectedEmailContent)).thenReturn(sendEmail);
                }
            }

        }
        if (isError) {
            assertThrows(RuntimeException.class, () -> {
                libraryService.checkoutResource(resourceId, memberEmail);
            }, "Expected an exception for " + testCaseId + " but none was thrown.");
        } else {
            boolean result = libraryService.checkoutResource(resourceId, memberEmail);
            assertEquals(expectedResult, result);
        }
    }

}

