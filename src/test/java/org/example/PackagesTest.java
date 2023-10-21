package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PackagesTest {

    Packages packages;


    @BeforeEach
    void setUp() {this.packages = new Packages();}

    @Test
    void testGetPackageId() {
        Assertions.assertEquals(packages.getPackageId(), 202);
    }

    @Test
    void testGetPackageType() {
        Assertions.assertEquals(packages.getPackageType(), "ADVENTURE");
    }

    @Test
    void testGetPackageName() {
        //packages.createPackage(201,"NATURE", "3 day Peninsula fun");
        Assertions.assertEquals(packages.getPackageName(), "2 Extreme sport in the North");

    }
    /*
    void testGetPackageName() {
        packages.createPackage(201,"NATURE", "3 day Peninsula fun");
        Assertions.assertEquals(packages.getPackageName(), "3 day Peninsula fun");

    }
    */
}
