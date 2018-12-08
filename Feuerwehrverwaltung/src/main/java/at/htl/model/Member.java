package at.htl.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; //Vor- und Zuname
    private String dienstgrad;
    private String mail;
    private LocalDate involvedSince;

    //region Constructors
    public Member(String name, String dienstgrad, String mail, LocalDate involvedSince) {
        this.name = name;
        this.dienstgrad = dienstgrad;
        this.mail = mail;
        this.involvedSince = involvedSince;
    }

    public Member(){

    }
    //endregion

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDienstgrad() {
        return dienstgrad;
    }

    public void setDienstgrad(String dienstgrad) {
        this.dienstgrad = dienstgrad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getInvolvedSince() {
        return involvedSince;
    }

    public void setInvolvedSince(LocalDate involvedSince) {
        this.involvedSince = involvedSince;
    }
    //endregion
}
