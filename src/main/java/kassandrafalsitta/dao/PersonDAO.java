package kassandrafalsitta.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import kassandrafalsitta.entities.Person;

import java.util.List;
import java.util.UUID;

public class PersonDAO {
    //attributi
    private final EntityManager em;

    //costruttore
    public PersonDAO(EntityManager em) {
        this.em = em;
    }

    //metodi
    public void save(List<Person> personList) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (Person person : personList) {
            em.persist(person);
        }
        transaction.commit();
        System.out.println("Le persone sono state aggiunte con successo");
    }

    public Person findById(UUID personId) {
        Person personFound = null;
        try {
            personFound = em.find(Person.class, personId);

            if (personFound == null) System.out.println("La persona con id: " + personId + " non è stato trovato");
            else {
                System.out.println("\nEcco la'persona che hai cercato:");
                System.out.println(personFound);
            }

        } catch (Exception e) {
            System.out.println("Non è stato possibile cercare la persona con id: " + personId + "\n");
        }
        return personFound;
    }
}
