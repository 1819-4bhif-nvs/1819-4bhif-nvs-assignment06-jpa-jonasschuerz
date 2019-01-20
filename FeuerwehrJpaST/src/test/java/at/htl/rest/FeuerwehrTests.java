package at.htl.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class FeuerwehrTests {

    private Client client;
    private WebTarget tut;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.tut = client.target("http://localhost:8080/feuerwehr/api/");
    }

    @Test
    public void t01_deleteMember(){
        Response response = tut.path("member/deleteMember/2").request().delete();
        assertThat(response.getStatus(),is(500));
    }

    @Test
    public void t02_getAll() {
        Response response = this.tut.path("member/findAll")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void t03_getById() {
        Response response = this.tut
                .path("member/1")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertThat(response.getStatus(), is(200));
        JsonObject payload = response.readEntity(JsonObject.class);
        assertThat(payload.getString("name"), is("Max Meier"));
    }

    @Test
    public void t08_deleteOperation(){
        Response response = tut.path("operation/deleteOperation/1").request().delete();
        assertThat(response.getStatus(),is(500));
    }

    @Test
    public void t04_getAll() {
        Response response = this.tut.path("operation/findAll")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void t05_getById() {
        Response response = this.tut
                .path("operation/1")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertThat(response.getStatus(), is(200));
        JsonObject payload = response.readEntity(JsonObject.class);
        assertThat(payload.getString("typeOfMission"), is("BRANDMELDEANLAGE"));
    }

    @Test
    public void t06_getAll() {
        Response response = this.tut.path("vehicle/findAll")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void t07_getById() {
        Response response = this.tut
                .path("vehicle/1")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertThat(response.getStatus(), is(200));
        JsonObject payload = response.readEntity(JsonObject.class);
        assertThat(payload.getString("brand"), is("Mercedes"));
    }

    @Test
    public void t09_deleteVehicle(){
        Response response = tut.path("vehicle/deleteVehicle/1").request().delete();
        assertThat(response.getStatus(),is(500));
    }







}
