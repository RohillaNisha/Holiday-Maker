package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class PackagesTest {

    Packages packages;
    String packageType = " ";
    String packageName = " ";

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

    @Test
    @DisplayName("Should demonstrate a method Fetch a package in the Database by its PackageType")
    public void testFetchPackageByPackageType(){

        Package fetchedPackage = packages.fetchPackageByPackageType(packageType);
        System.out.println("The fetched package is of PackageType: " + fetchedPackage );
        Assertions.assertEquals(fetchedPackage.getPackageType(), "ADVENTURE" ); {
        };

    }
    @Test
    @DisplayName("Should demonstrate a method Fetch a package in the Database by its PackageName")
    public void testFetchPackageByPackageName(){

        Package fetchedPackage = packages.fetchPackageByPackageName(packageName);
        System.out.println("The fetched package is of PackageType: " + fetchedPackage );
        Assertions.assertEquals(fetchedPackage.getPackageName(), "Explore culture Skåne"); {

        };

    }
    /*
    @Test
    @DisplayName("Should demonstrate a method Get a package in the Database by its PackageName")
    void testGetPackagesByPackagesName() {
        Assertions.assertEquals() {
            fail("Not implemented yet");
        }
    }

    @Test
    void testGetPackagesByPackagesType() {
        Assertions.assertEquals(){
            fail("Not implemented yet");
        }
    }
*/

    /*
    void testGetPackageName() {
        packages.createPackage(201,"NATURE", "3 day Peninsula fun");
        Assertions.assertEquals(packages.getPackageName(), "3 day Peninsula fun");

    }
*/



/*
// Nested Test   feature of JUnit 5
// Nested tests allow us to group specific types of tests together inside a larger class,
// (there are lots of reasons why we want to do this)

@ParameterizedTest
    @DisplayName("Should not create shapes with invalid numbers of sides");
    @ValueSource(ints =  {0, 1, 2, Integer.MAX_VALUE})
    void shouldNotCreateShapesWithInvalidNumbersOfSides(int expectedNumberOfSides) {

    assertThrows(IllegalArgumentException.class,
            () -> new Shape(expectedNumbersOfSides));
    }

    @Nested
    // The nested class can contain fields and we can use these to store values that all the tests inside this inner class will need
    @DisplayName("When a shape has been created")
    class WhenShapeExists {
        private final Shape shape = new Shape(4)

        @Nested
        @DisplayName("Should allow")
        class ShouldAllow {

            @Test
            @DisplayName("Seeing the number of sides")
            void seeingTheNumbersOfSides() {
                assertEquals(4, shape.numberOfSides());

            }

            @Test
            @DisplayName("Seeing the description")
            void seeingTheDescription() {
                  assertEquals("Square", shape.description());

            }
        }
        @Nested
        @DisplayName("Should not")
        class ShouldNot {
            @Test
            @DisplayName("be equal to another shape with the same number of sides")
            void beEqualToAnotherShapeWithTheSameNumberOfSides() {
                    assertNotEquals(new Shape(4), shape));
                    }

        }
    }
}


*/

/*
//Example of a parameterized Test
    // If we want to do the same set of checks on different sets of data
    // for this we use parameterised tests
    //  pass data into the test as parameters and with JUnit 5 there are a number of different ways to do this

    @ParameterizedTest  (name = "{0}" )// ParametrizedTest annotation instead
    @DisplayName("Should create ")
    @ValueSource(ints =  {3, 4, 5, 8, 14})  // Creates an Array JUnit 5 supports many different types of Arrays
    // in this Array we hardcode several integers!
    void shouldCreateShapesWithDifferentNumbersOfSides(int expectedNumberOfSides) {
        Shape shape = new Shape(expectedNumberOfSides);
        assertEquals(expectedNumberOfSides, shape.numberOfSides());
        fail("Not implemented!");
    }

// We can use assertAll to group a number of assertions and make sure  they're all run
// one way of performing multiple checks
    @Test
    @DisplayName("should check all items in the list")
    void shouldCheckAllItemsInTheList() {

        List<Integer> numbers = List.of(2, 3, 5, 7);

        Assertions.assertAll(() -> assertEquals(2, numbers.get(0)),
                             () -> assertEquals(3, numbers.get(1)),
                             () -> assertEquals(5, numbers.get(2)),
                             () -> assertEquals(7, numbers.get(3)));
    }

// Same type of check on same type of dataset
    @Test
    @DisplayName("should check all items in the list")
    void shouldCheckAllItemsInTheList() {
        List<Integer> numbers = List.of(2, 3, 5, 7);
        Assertions.assertEquals(2, numbers.get(0));
        Assertions.assertEquals(3, numbers.get(1));
        Assertions.assertEquals(5, numbers.get(2));
        Assertions.assertEquals(7, numbers.get(3));
        }

// Ex från Nishas UsersTest
        @Test
        public void testSearchUserByPersonalNumber(){

            User fetchedUser = users.searchUserByPersonalNumber(personalNumber);
            System.out.println("fetched test user is: "+ fetchedUser );
            Assertions.assertEquals(fetchedUser.getPersonalNumber(), "NR1234" );

        }

*/
}
