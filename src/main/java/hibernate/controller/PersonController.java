package hibernate.controller;

import hibernate.entity.Person;
import hibernate.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller with main CRUD operations. Use methods from Service class.
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {


    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    /**
     *
     * @return List of persons in Json.
     */
    @GetMapping("/persons")
    public List<Person> showAllPersons() {
        return personService.getAllPersons();
    }

    /**
     *
     * @return List of persons with address in Json.
     */
    @GetMapping("/persons/address")
    public List<Person> showAllPersonsWithAddress() {
        return personService.getAllPersonsWithAddress();
    }

    /**
     *
     * @return single person in Json.
     */
    @GetMapping("/persons/{id}")
    public Person showPerson(@PathVariable int id){
        Person person = personService.getPerson(id);
        if (person == null){
            throw new RuntimeException("No such person id");
        }
        return person;
    }

    /**
     *
     * @return single person with address in Json.
     */
    @GetMapping("/persons/{id}/address")
    public Person showPersonWithAddress(@PathVariable int id){
        Person person = personService.getPersonWithAddress(id);
        if (person == null){
            throw new RuntimeException("No such person id");
        }
        return person;
    }

    /**
     *
     * @param person data for person to add passed in json file using POST method.
     * @return - person.
     */
    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person){
        personService.savePerson(person);
        return person;
    }

    /**
     *
     * @param person data for person to modify passed in json file using PUT method.
     * @return - person.
     */
    @PutMapping("/persons")
    public Person updateEmployee(@RequestBody Person person){
        personService.savePerson(person);
        return person;
    }

    /**
     *
     * @param id person id, to be deleted
     * @return String with successful deletion of specified person id.
     */
    @DeleteMapping("/persons/{id}")
    public String deletePerson(@PathVariable int id){
        Person person = personService.getPerson(id);
        if (person == null){
            throw new RuntimeException("No such person id");
        }
        personService.deletePerson(id);
        return "Person with id = " +id + " has been deleted";
    }

}
