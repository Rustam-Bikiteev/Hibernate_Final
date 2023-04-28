package hibernate.service;

import hibernate.dao.PersonDAO;
import hibernate.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class - takes the DAO methods, to implement business logic.
 */
@Service
public class PersonServiceImpl implements PersonService {


    private PersonDAO personDAO;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }

    @Override
    public List<Person> getAllPersonsWithAddress() {
        return personDAO.getAllPersonsWithAddress();
    }

    @Override
    public void savePerson(Person person) {
        personDAO.savePerson(person);
    }

    @Override
    public Person getPerson(int id) {
        return personDAO.getPerson(id);
    }

    @Override
    public Person getPersonWithAddress(int id) {
        return personDAO.getPersonWithAddress(id);
    }

    @Override
    public void deletePerson(int id) {
        personDAO.deletePerson(id);
    }
}
