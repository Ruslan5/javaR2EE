package com.mir.springserver.rest;

import com.mir.springserver.config.security.jwt.JwtTokenProvider;
import com.mir.springserver.model.User;
import com.mir.springserver.model.dto.AuthenticationRequestDto;
import com.mir.springserver.model.dto.UserDTO;
import com.mir.springserver.model.dto.UserDTOToUser;
import com.mir.springserver.service.RoleService;
import com.mir.springserver.service.UserService;
import com.mir.springserver.model.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Path("/auth/")
@Produces({MediaType.APPLICATION_JSON})
@RestController
public class AuthenticationRestController {
    private static Logger logger = Logger.getLogger(AuthenticationRestController.class);

    private final RoleService roleService;
    private final UserDTOToUser userDTOToUser;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationRestController(RoleService roleService,
                                        UserDTOToUser userDTOToUser, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.roleService = roleService;
        this.userDTOToUser = userDTOToUser;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    //    @Autowired
//    public AuthenticationRestController(
//            AuthenticationManager authenticationManager,
//            JwtTokenProvider jwtTokenProvider, UserService userService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.userService = userService;
//    }

    @POST
    @Path("register")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response registerUser(UserDTO userDTO, @Context UriInfo uriInfo) {
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

    @POST
    @Path("login")
    public Response login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            String pass = requestDto.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, pass));
            User user = userService.findByLogin(username);

            if (user == null) {
                throw new UsernameNotFoundException(
                        "User with username: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username,
                    pass, Collections.singletonList(user.getRole()));
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            response.put("roles", user.getRole().getName());
            return Response.ok(response).build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
