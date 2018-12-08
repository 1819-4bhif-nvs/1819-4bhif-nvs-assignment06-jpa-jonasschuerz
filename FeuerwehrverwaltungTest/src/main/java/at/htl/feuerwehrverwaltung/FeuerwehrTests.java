package at.htl.feuerwehrverwaltung;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static sun.nio.cs.Surrogate.is;

public class FeuerwehrTests {

    private Client client;
    private WebTarget target;

    private final JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/feuerwehrverwaltung/api");
    }

    @Test
    public void t01_AddAndRemoveMember(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("name","Jonas Schürz");
        t.add("dienstgrad",")OFM");
        t.add("mail","mustermann.d@test.com");
        t.add("involvedSince", LocalDate.now(DateTimeFormatter.ISO_DATE_TIME));
        JsonObject obj = t.build();

        postGetDeleteGet(obj, "members", new String[]{"/name","/dienstgrad","/mail","/involvedSince"});
    }

    private void postGetDeleteGet(JsonObject toPost, String path, String[] compareVals){

        Response resp = this.tut.path(path).request(MediaType.APPLICATION_JSON).post(Entity.json(toPost.toString()));
        assertThat(resp.getStatus(), is(200));
        JsonObject inserted = resp.readEntity(JsonObject.class);
        assertThat(inserted, is(notNullValue()));
        int id = inserted.getInt("id");

        JsonObjectBuilder exBuilder = Json.createObjectBuilder(toPost);
        exBuilder.add("id",id);

        Response get = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get();

        JsonObject expected = exBuilder.build();
        JsonObject actual = get.readEntity(JsonObject.class);

        for(String s:compareVals){
            assertThat(actual.getValue(s), equalTo(expected.getValue(s)));
        }

        Response del = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).delete();
        assertThat(del.getStatus(), is(200));

        Response getDel = this.tut.path(path).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get();
        assertThat(getDel.getStatus(), is(404));
    }


}
