package hibernate.dao;

import hibernate.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Methods to CRUD with bd.
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public PersonDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * Select all persons from BD
     *
     * @return - List of persons.
     */
    @Override
    @Transactional
    public List<Person> getAllPersons() {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("from Person", Person.class);
        List<Person> persons = query.getResultList();
        return persons;
    }

    /**
     * Select all persons join the address entity from BD
     *
     * @return - List of persons.
     */
    @Override
    @Transactional
    public List<Person> getAllPersonsWithAddress() {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("from Person p left join fetch p.address", Person.class);
        List<Person> persons = query.getResultList();
        return persons;
    }

    /**
     * Save the person to BD.
     */
    @Override
    @Transactional
    public void savePerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(person);
    }

    /**
     * @param id Select person with id form Bd.
     * @return person.
     */
    @Override
    @Transactional
    public Person getPerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    /**
     * @param id Select person with id form Bd join the address table.
     * @return person.
     */
    @Override
    @Transactional
    public Person getPersonWithAddress(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("select p from Person p left join fetch p.address where p.id =:id", Person.class);
        Person person = query.setParameter("id", id).getSingleResult();
        return person;
    }


    /**
     * @param id delete person from BD with id.
     */
    @Override
    @Transactional
    public void deletePerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("delete from Person where id = :personId");
        query.setParameter("personId", id);
        query.executeUpdate();
    }
}
