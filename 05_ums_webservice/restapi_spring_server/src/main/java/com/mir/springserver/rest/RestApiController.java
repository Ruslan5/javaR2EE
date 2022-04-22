package com.mir.springserver.rest;

import com.mir.springserver.controller.MainController;
import com.mir.springserver.model.Role;
import com.mir.springserver.model.User;
import com.mir.springserver.model.dto.UserDTO;
import com.mir.springserver.model.dto.UserDTOToUser;
import com.mir.springserver.service.RoleService;
import com.mir.springserver.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RestController
@Path("user")
public class RestApiController {
    private static Logger logger = Logger.getLogger(MainController.class);
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOToUser userDTOToUser;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<User>> getUserList() {
        List<User> user = userService.findAll();
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }

    @GET
    @Path("/{login}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User findUserByLogin(@PathParam("login") String login) {
        return userService.findByLogin(login);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addEmployee(UserDTO user, @Context UriInfo uriInfo) {
        Role role = roleService.findById(Long.parseLong(user.getRole()));
        logger.debug("get user" + user);
        User user1 = userDTOToUser.convert(user, role);
        userService.create(user1);
        logger.debug("created user" + user1);
        return Response.status(Response.Status.CREATED.getStatusCode())
                .header("Location", String.format("%s/%s"
                        , uriInfo.getAbsolutePath().toString()
                        , user.getId())).build();
    }

    @PUT
    @Path("/{login}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateEmployee(UserDTO userDTO, @PathParam("login") String login) {
        Role role = roleService.findById(Long.parseLong(userDTO.getRole()));
        User user = userDTOToUser.convertEdit(login, userDTO, role);
        userService.update(user);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{login}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteEmployee(@PathParam("login") String login) {
        User user = userService.findByLogin(login);
        userService.remove(user);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
