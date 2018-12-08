package at.htl.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String typeOfMission; //Einsatzart
    @Column()
    private String alarmText;
    @Column()
    private Integer alert; //Alarmstufe
    @Column()
    private String ort;
    @Column()
    private LocalDate time;

    public Mission(){

    }

    public Mission(String typeOfMission, String alarmText, Integer alert, String ort, LocalDate time) {
        this.typeOfMission = typeOfMission;
        this.alarmText = alarmText;
        this.alert = alert;
        this.ort = ort;
        this.time = time;
    }

    //region Getter and Setter
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfMission() {
        return typeOfMission;
    }

    public void setTypeOfMission(String typeOfMission) {
        this.typeOfMission = typeOfMission;
    }

    public String getAlarmText() {
        return alarmText;
    }

    public void setAlarmText(String alarmText) {
        this.alarmText = alarmText;
    }

    public Integer getAlert() {
        return alert;
    }

    public void setAlert(Integer alert) {
        this.alert = alert;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
    //endregion


}
