package com.mir.restapispringclient;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class RestClientTest {

    public static final int HTTP_OK = 201;
    public static final int HTTP_OK_UPDATE = 200;

    RestClient restClient = new RestClient();

    @Test
    public void testGetAllUsers() {
        Response user = restClient.getAllJsonUser();
        assertEquals(HTTP_OK_UPDATE, user.getStatus());
    }

    @Test
    public void testCreateUsers() {
        UserDTO userDTO = new UserDTO("login1", "pass1", "mail@m", "fname1", "lname1", new Date(2000-11-11), "1");
        Response response = restClient.createJsonUser(userDTO);
        assertEquals(HTTP_OK, response.getStatus());
    }

    @Test
    public void testUpdateUsers() {
        String login = "login2";
        UserDTO userDTO = new UserDTO(login, "pass22", "mail@m", "fname3", "lname3", new Date(2000-11-11), "2");
        Response response = restClient.updateJsonUser(userDTO);
        assertEquals(HTTP_OK_UPDATE, response.getStatus());
    }


    @Test
    public void testFindUserByLogin() {
        String login = "login2";
        User user = restClient.getJsonUser(login);
        assertEquals(login, user.getLogin());
    }

    @Test
    public void testDeleteUsers() {
        String login = "login1";
        Response user = restClient.deleteJsonUser(login);
        assertEquals(HTTP_OK_UPDATE, user.getStatus());
    }
}


