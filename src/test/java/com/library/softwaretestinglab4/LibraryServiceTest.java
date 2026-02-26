package com.library.softwaretestinglab4;

import com.library.softwaretestinglab4.library.EmailProvider;
import com.library.softwaretestinglab4.library.LibraryService;
import com.library.softwaretestinglab4.library.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    private EmailProvider mockEmailProvider;

    @Mock
    private ResourceRepository mockResourceRepository;

    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        libraryService = new LibraryService(mockEmailProvider, mockResourceRepository);
    }


}
