package kassandrafalsitta.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import kassandrafalsitta.entities.Event;

import java.util.List;
import java.util.UUID;

public class EventDAO {
    //attributi
    private final EntityManager em;

    //costruttore
    public EventDAO(EntityManager em) {
        this.em = em;
    }

    //metodi
    public void save(List<Event> eventList) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (Event event : eventList) {
            em.persist(event);
        }
        transaction.commit();
        System.out.println("gli eventi sono stati aggiunti con successo");
    }

    public Event findById(UUID eventId) {
        Event eventFound = null;
        try {
            eventFound = em.find(Event.class, eventId);

            if (eventFound == null) System.out.println("L'evento con id: " + eventId + " non è stato trovato");
            else {
                System.out.println("\nEcco l'evento che hai cercato:");
                System.out.println(eventFound);
            }

        } catch (Exception e) {
            System.out.println("Non è stato possibile cercare l'evento con id: " + eventId + "\n");
        }
        return eventFound;
    }

    public void findByIdAndDelete(UUID eventId) {
        try {
            Event eventFound = this.findById(eventId);
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(eventFound);
            transaction.commit();
            System.out.println("L'evento con id: " + eventFound.getTitle() + "  è stato rimosso con successo");

        } catch (IllegalArgumentException e) {
            System.out.println("l'elemento da eliminare con id: " + eventId + " non esiste");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
