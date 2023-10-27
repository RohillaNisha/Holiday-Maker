package org.example.booking;

import org.example.Database;
import org.example.Package;
import org.example.Packages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class PackageBookingTest {
    private Database db;
    private Database mockDatabase;

    private List<Package> availablePackages = new ArrayList<>();


   // private PackageBookingProcess packageBookingProcess; // ???

    @BeforeEach
    void setUp() {
        mockDatabase = mock(Database.class);

        db = new Database();
       // packageBookingProcess = new PackageBookingProcess();  // ???

    }
    @Test
    void testGetPackagesFromDataBase() {
        List<Package> mockPackages = new ArrayList<>();
        mockPackages.add(new Package("NATURE","3 day Peninsula fun"));
        mockPackages.add(new Package("CULTURE","Explore culture in Skåne"));
        mockPackages.add(new Package("ADVENTURE","3 Extreme sport in the North"));
        mockPackages.add(new Package("ADVENTURE","2 Extreme sport in the North"));


        when(mockDatabase.getListOfAllPackages()).thenReturn(mockPackages);
        assertEquals(4, mockPackages.size());
        System.out.println("The size of theList of available packages: " + mockPackages.size());
    }


}
