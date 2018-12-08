package at.htl.model;

import javax.persistence.*;

@Entity
public class MissionStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    private Mission mission;

    //region Constructors
    public MissionStatistics(){

    }

    public MissionStatistics(Member member, Mission mission) {
        this.member = member;
        this.mission = mission;
    }

    //endregion


    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
    //endregion
}
