package kassandrafalsitta.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import kassandrafalsitta.entities.Location;

import java.util.List;
import java.util.UUID;

public class LocationDAO {
    //attributi
    private final EntityManager em;

    //costruttore
    public LocationDAO(EntityManager em) {
        this.em = em;
    }

    //metodi
    public void save(List<Location> locationList) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (Location location : locationList) {
            em.persist(location);
        }
        transaction.commit();
        System.out.println("Le location sono state aggiunte con successo");
    }

    public Location findById(UUID locationId) {
        Location locationFound = null;
        try {
            locationFound = em.find(Location.class, locationId);

            if (locationFound == null) System.out.println("La località con id: " + locationId + " non è stato trovato");
            else {
                System.out.println("\nEcco la'località che hai cercato:");
                System.out.println(locationFound);
            }

        } catch (Exception e) {
            System.out.println("Non è stato possibile cercare la località con id: " + locationId + "\n");
        }
        return locationFound;
    }
}
