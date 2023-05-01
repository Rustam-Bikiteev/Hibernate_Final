package hibernate.dao;

import hibernate.entity.Person;

import java.util.List;

/**
 * DAO interface to communicate with BD.
 */
public interface PersonDAO {

     List<Person> getAllPersons();
     List<Person> getAllPersonsWithAddress();
     void savePerson(Person person);
     Person getPerson(int id);
     Person getPersonWithAddress(int id);
     void deletePerson(int id);

}
