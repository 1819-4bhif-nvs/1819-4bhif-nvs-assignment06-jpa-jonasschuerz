package at.htl.rest;

import at.htl.model.Vehicle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("vehicle")
@Stateless
public class VehicleEndpoint {
    @PersistenceContext
    EntityManager em;

    @Path("findAll")
    @GET
    public Response findAll() {
        TypedQuery<Vehicle> query = em.createNamedQuery("Vehicle.findAll", Vehicle.class);
        List<Vehicle> vehicles = query.getResultList();
        return Response.ok().entity(vehicles).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") long id) {
        Vehicle vehicle = em.find(Vehicle.class, id);
        if (vehicle == null) {
            return Response.status(404).build();
        }
        return Response.ok().entity(vehicle).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") long id) {
        Vehicle vehicle = em.find(Vehicle.class, id);
        if (vehicle == null) {
            return Response.status(404).build();
        }
        em.remove(vehicle);
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Vehicle vehicle){
        em.persist(vehicle);
        return Response.ok().entity(vehicle).build();
    }

}