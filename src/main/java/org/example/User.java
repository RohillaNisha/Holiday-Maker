package org.example;

import java.time.LocalDate;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    private String contactNumber, personalNumber, address;
    private LocalDate dob;

    public User(int id, String firstName, String lastName, String email, String contactNumber, String personalNumber, String address, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.personalNumber = personalNumber;
        this.address = address;
        this.dob = dob;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
