package kassandrafalsitta.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.*;

import java.util.*;
import java.util.function.Supplier;

@Entity
@Table(name = "locations")
public class Location {
    //supplier
    public static Supplier<Location> locationSupplierWithScanner = () -> {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("inserisci nome location");
                String name = sc.nextLine();
                System.out.println("inserisci città");
                String city = sc.nextLine();
                return new Location(name, city);
            } catch (InputMismatchException e) {
                System.out.println("inserisci i valori correttamente");
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    };
    static Faker fk = new Faker();
    public static Supplier<Location> locationSupplier = () -> new Location(fk.name().name(), fk.address().city());
    static Random r = new Random();
    //attributi
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String city;

    @OneToMany(mappedBy = "locationId")
    private List<Event> eventList;

    //costruttore
    public Location() {
    }

    public Location(String name, String city) {
        this.name = name;
        this.city = city;
    }

    //metodi
    public static List<Location> createLocation() {
        List<Location> locationList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int numOfLocations;
        while (true) {
            System.out.println("quante location vuoi creare?");
            try {
                numOfLocations = Integer.parseInt(sc.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("inserisci un numero valido");

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }

        }

        System.out.println("1. creali tu \n 2. creali random");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    for (int i = 0; i < numOfLocations; i++) {
                        locationList.add(locationSupplierWithScanner.get());
                    }
                    break;
                case 2:
                    for (int i = 0; i < numOfLocations; i++) {
                        locationList.add(locationSupplier.get());
                    }
                    System.out.println("location create con successo");
                    break;
                default:
                    System.out.println("scelta non valida riprova!");
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("inserisci un numero valido");

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        return locationList;
    }
    //getter e setter

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //to string


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
