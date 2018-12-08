package at.htl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TripleCombinationPumper extends Vehicle { //Tanklöschfahrzeug
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tank;
    private String tragkraftspritze;
    private String type;

    //region Constructors
    public TripleCombinationPumper(Long mileage, LocalDateTime firstRegistration, String brand, Mission mission, Long tank, String tragkraftspritze, String type) {
        super(mileage, firstRegistration, brand, mission);
        this.tank = tank;
        this.tragkraftspritze = tragkraftspritze;
        this.type = type;
    }

    public TripleCombinationPumper() {
    }
    //endregion

    //region Getter and Setter
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getTank() {
        return tank;
    }

    public void setTank(Long tank) {
        this.tank = tank;
    }

    public String getTragkraftspritze() {
        return tragkraftspritze;
    }

    public void setTragkraftspritze(String tragkraftspritze) {
        this.tragkraftspritze = tragkraftspritze;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //endregion
}
