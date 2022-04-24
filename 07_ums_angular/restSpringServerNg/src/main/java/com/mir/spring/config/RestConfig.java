package com.mir.spring.config;

import com.mir.spring.rest.RestApiController;
import com.mir.spring.rest.AuthenticationRestController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/resources/*")
public class RestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(
                Arrays.asList(
                        RestApiController.class, AuthenticationRestController.class));
    }
}