package at.htl.model;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = "Commando.findAll", query = "select c from Commando c join Member p on p.id = c.id"),
})
@Entity(name = "Commando")
public class Commando extends Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String function; //Funktion zb. Kommandant, Schriftführer, Kassier,...
    @Column
    private String dienstgrad;

    public Commando(String name, String dienstgrad, String mail, LocalDate involvedSince, String function) {
        super(name, dienstgrad, mail, involvedSince);
        this.function = function;
    }

    public Commando() {
    }

    //region Getter and Setter
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
    //endregion

}
