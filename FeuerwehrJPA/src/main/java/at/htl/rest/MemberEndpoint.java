package at.htl.rest;

import at.htl.model.Member;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("member")
@Stateless
public class MemberEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Path("findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        TypedQuery<Member> query = em.createNamedQuery("Member.findAll", Member.class);
        List<Member> members = query.getResultList();
        return Response.ok().entity(members).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        Member member = em.find(Member.class, id);
        if (member == null) {
            return Response.status(404).build();
        }
        return Response.ok().entity(member).build();
    }

    @DELETE
    @Path("/deleteMember/{id}")
    public Response deleteById(@PathParam("id") long id) {
        Member member = em.find(Member.class, id);
        if (member == null) {
            return Response.status(404).build();
        }
        em.remove(member);
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Member member){
        em.persist(member);
        return Response.ok().entity(member).build();
    }

}
