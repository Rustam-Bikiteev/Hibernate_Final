package hibernate.service;

import hibernate.entity.Person;

import java.util.List;

/**
 * Service interface - contains main methods to operate with data.
 */
public interface PersonService {
     List<Person> getAllPersons();
     List<Person> getAllPersonsWithAddress();
     void savePerson(Person person);
     Person getPerson(int id);
     Person getPersonWithAddress(int id);
     void deletePerson(int id);
}
