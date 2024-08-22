package kassandrafalsitta.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import kassandrafalsitta.entities.Participation;

import java.util.List;
import java.util.UUID;

public class PartecipationDAO {
    //attributi
    private final EntityManager em;

    //costruttore
    public PartecipationDAO(EntityManager em) {
        this.em = em;
    }

    //metodi
    public void save(List<Participation> partecipationList) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (Participation participation : partecipationList) {
            em.persist(participation);
        }
        transaction.commit();
        System.out.println("Le partecipazioni sono state aggiunte con successo");
    }

    public Participation findById(UUID partecipationId) {
        Participation partecipationFound = null;
        try {
            partecipationFound = em.find(Participation.class, partecipationId);

            if (partecipationFound == null)
                System.out.println("La partecipazione con id: " + partecipationId + " non è stato trovato");
            else {
                System.out.println("\nEcco la'partecipazione che hai cercato:");
                System.out.println(partecipationFound);
            }

        } catch (Exception e) {
            System.out.println("Non è stato possibile cercare la partecipazione con id: " + partecipationId + "\n");
        }
        return partecipationFound;
    }
}
