package at.htl.model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mileage; //Kilometerstand
    private LocalDateTime firstRegistration; //Erstzulassung
    private String brand;

    @ManyToOne
    private Mission mission; //Einsatz

    //region Constructors
    public Vehicle(Long mileage, LocalDateTime firstRegistration, String brand, Mission mission) {
        this.mileage = mileage;
        this.firstRegistration = firstRegistration;
        this.brand = brand;
        this.mission = mission;
    }

    public Vehicle(){

    }
    //endregion


    //region Getter and Setter
    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public LocalDateTime getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(LocalDateTime firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    //endregion
}
