package at.htl.rest;

import at.htl.model.Mission;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("missions")
public class MissionEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    public Response listMissions(){
        TypedQuery query = em.createQuery("select m from Mission m", Mission.class);
        List<Mission> missions = query.getResultList();
        return Response.ok().entity(missions).build();
    }
}
