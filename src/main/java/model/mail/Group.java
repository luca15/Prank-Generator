package model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents groups of person.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public class Group {
    private List<Person> persons = new ArrayList<>();

    public Group() {}

    public Group(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * This method returns the list of persons of the group.
     *
     * @return the list of persons of the group
     */
    public List<Person> getListOfPersons() {
        return persons;
    }

    /**
     * This method allows to add a person in the group.
     *
     * @param person the person that we want to add
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * This method allows to add a list of persons in the group.
     *
     * @param persons the list of persons that we want to add
     */
    public void addPersons(List<Person> persons) {
        this.persons.addAll(persons);
    }
}
