package edu.hcmut.bookstore.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "Person")
public class Person {
    // Table 'Person' used in this example contains 4 columns:
    // id, firstname, lastname and age.
    // The convention that Spring uses to convert class's field names to table's column names:
    // Camel-case names are converted to snake-case names. E.g: "helloWorld" would be converted
    // to "hello_world".

    @Id
    private final Integer id;
    private String firstname;
    private String lastname;
    private int age;

//    public Person(String firstname, String lastname, int age) {
//        this.id = null;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.age = age;
//    }

    public Person(Integer id, String firstname, String lastname, int age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public Person withId(int id) {
        return new Person(id, firstname, lastname, age);
    }

    public Integer getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public int getAge() {
        return age;
    }
}
