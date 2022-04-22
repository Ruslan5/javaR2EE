package com.mir.restapispringclient;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RestClient {

    /**
     *   link for local use
     */
    private static final String REST_URI = "http://localhost:8080/resources/user";

    /**
     * link for docker use
     */
//    private static final String REST_URI = "http://mirzoiev-tomcat:8080/resources/user";
    private Client client = ClientBuilder.newClient();

    public Response getAllJsonUser() {
        return client.target(REST_URI).request(MediaType.APPLICATION_JSON).get();
    }

    public User getJsonUser(String login) {
        return client.target(REST_URI).path(login).request(MediaType.APPLICATION_JSON).get(User.class);
    }

    public Response createJsonUser(UserDTO user) {
        return client.target(REST_URI).request(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON));
    }

    public Response updateJsonUser(UserDTO user) {
        return client.target(REST_URI).path(user.getLogin()).request(MediaType.APPLICATION_JSON).put(Entity.entity(user, MediaType.APPLICATION_JSON));
    }

    public Response deleteJsonUser(String login) {
        return client.target(REST_URI).path(login).request(MediaType.APPLICATION_JSON).delete();
    }

}
