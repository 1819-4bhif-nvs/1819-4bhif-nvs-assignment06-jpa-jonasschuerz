package at.htl.business;

import at.htl.model.Commando;
import at.htl.model.Member;
import at.htl.model.Mission;
import at.htl.model.MissionStatistics;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Singleton
@Startup
public class InitBean {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init(){
        System.out.println("********** Init started");

        DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Member meier = new Member("Max Meier","Oberfeuerwehrmann","m.meier@mail.com", LocalDate.parse("1990-10-01", fm));
        Member meierF = new Member("Friedrich Meier","Oberamtswalter","f.meier@mail.com", LocalDate.parse("1994-03-01", fm));
       // Commando mustermann = new Commando("Max Mustermann","Loeschmeister","m.mustermann@mail.com", LocalDate.parse("1994-03-01", fm),"Amtswalter");
        Mission mission1 = new Mission("BRANDMELDEANLAGE","Brandmelder UO-52-1 ausgelöst",1,"Mustercitystraße 5, Mustercity",LocalDate.parse("2018-04-01", fm));
        Mission mission2 = new Mission("FAHRZEUGBERGUNG","Traktor in Teich",1,"Mustercitystraße 2, Mustercity",LocalDate.parse("2017-07-21", fm));
        Mission mission3 = new Mission("WASSERSCHADEN","Wasser im Keller",1,"Mustercitystraße 1, Mustercity",LocalDate.parse("2018-12-03", fm));

        MissionStatistics m1 = new MissionStatistics(meier,mission1);
        MissionStatistics m2 = new MissionStatistics(meier,mission2);
        MissionStatistics m3 = new MissionStatistics(meier,mission3);
        MissionStatistics m4 = new MissionStatistics(meierF,mission1);
        MissionStatistics m5 = new MissionStatistics(meierF,mission3);



        em.persist(meier);
        em.persist(meierF);
       // em.persist(mustermann);
        em.persist(mission1);
        em.persist(mission2);
        em.persist(mission3);
        em.persist(m1);
        em.persist(m2);
        em.persist(m3);
        em.persist(m4);
        em.persist(m5);

    }
}
