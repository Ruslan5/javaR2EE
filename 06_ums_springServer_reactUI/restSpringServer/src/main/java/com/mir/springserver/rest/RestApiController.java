package com.mir.springserver.rest;

import com.mir.springserver.model.User;
import com.mir.springserver.model.dto.UserDTO;
import com.mir.springserver.model.dto.UserDTOToUser;
import com.mir.springserver.service.RoleService;
import com.mir.springserver.service.UserService;
import com.mir.springserver.model.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

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

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@Path("/user")
public class RestApiController {
    private static Logger logger = Logger.getLogger(RestApiController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOToUser userDTOToUser;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseEntity<List<User>> getUserList() {
        List<User> user = userService.findAll();
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }

    @GET
    @Path("/{login}")
    @Produces({MediaType.APPLICATION_JSON})
    public User findUserByLogin(@PathParam("login") String login) {
        return userService.findByLogin(login);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addUser(UserDTO userDTO, @Context UriInfo uriInfo) {
        Role role = roleService.findById(Long.parseLong(userDTO.getRole()));
        logger.debug("get user" + userDTO);
        User user = userDTOToUser.convert(userDTO, role);
        userService.create(user);
        logger.debug("created user" + user);
        return Response.status(Response.Status.CREATED.getStatusCode())
                .header("Location", String.format("%s/%s",
                        uriInfo.getAbsolutePath().toString(),
                        userDTO.getId())).build();
    }

    @CrossOrigin()
    @PUT
    @Path("/{login}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateEmployee(UserDTO userDTO, @PathParam("login") String login) {
        Role role = roleService.findById(Long.parseLong(userDTO.getRole()));
        User user = userDTOToUser.convertEdit(login, userDTO, role);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.matches(userDTO.getPassword(), user.getPassword());
        userService.update(user);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{login}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteUser(@PathParam("login") String login) {
        User user = userService.findByLogin(login);
        userService.remove(user);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
