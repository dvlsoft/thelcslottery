package com.dvlsoft;

import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private String country;
    private List<String> ticketsNumbers;
    private int credits;

    public Person(String firstName, String lastName, String country, List<String> ticketsNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.ticketsNumbers = ticketsNumbers;
    }

    public int getCredits() {
        return credits;
    }

    public void addCredits(int credit) {
        this.credits += credit;
    }

    public List<String> getAllTicketsNumbers() {
        return new ArrayList<>(ticketsNumbers);
    }

    @Override
    public String toString() {
        return lastName + "," + firstName + "," + country + "," + credits;
    }

    @Override
    public int compareTo(Person person) {
        if (!this.lastName.equals(person.lastName)) {
            return this.lastName.compareTo(person.lastName);
        }
        if (!this.firstName.equals(person.firstName)) {
            return this.firstName.compareTo(person.firstName);
        }
        return this.country.compareTo(person.country);
    }
}
