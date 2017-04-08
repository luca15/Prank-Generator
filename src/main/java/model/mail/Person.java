package model.mail;

/**
 * This class represents a person in a address e-mail book.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public class Person {
    private String name;
    private String firstName;
    private String emailAddress;

    public Person(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
