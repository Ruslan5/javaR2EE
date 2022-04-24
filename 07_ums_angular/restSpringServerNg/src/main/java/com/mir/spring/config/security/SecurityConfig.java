package com.mir.spring.config.security;

import com.mir.spring.config.security.jwt.JwtTokenFilter;
import com.mir.spring.config.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource()).and()
                .httpBasic().disable()
                .csrf().disable()
        .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .authorizeRequests()
//                .antMatchers("/resources/auth/register").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/resources/auth/login").permitAll()
//                .antMatchers("/resources/auth/logout").permitAll()
//                .antMatchers("/main").hasAnyAuthority("user, admin")
//                .antMatchers("/").permitAll()
//                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/resources/user").permitAll()
//                .antMatchers("/resources/add-user").permitAll()
//                .antMatchers("/main/**").permitAll();

                .authorizeRequests()
                .antMatchers("/resources/auth/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/resources/auth/login").permitAll()
                .antMatchers("/resources/auth/logout").permitAll()
                .antMatchers("/main").hasAnyAuthority("user, admin")
                .antMatchers("/resources/user").hasAnyAuthority("user", "admin")
                .antMatchers("/resources/").hasAnyAuthority("user", "admin")
                .antMatchers("/resources/**").hasAuthority("admin")
                .antMatchers("/resources/user").hasAuthority("admin")
                .antMatchers("/resources/add-user").hasAuthority("admin")
                .antMatchers("/main/**").hasAuthority("admin")
                .anyRequest().authenticated();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(getDaoAuthenticationProvider());
    }

    /**
     * security config for Spring MVC
     * @return
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().configurationSource(corsConfigurationSource()).and().authorizeRequests().antMatchers("/").authenticated()
//                .antMatchers("/registration").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/main").hasAnyAuthority("user, admin")
//                .antMatchers("/main/**").hasAuthority("admin")
//                .anyRequest().authenticated()
//                .and().csrf().disable()
//                .formLogin().loginPage("/login")
//                .defaultSuccessUrl("/main")
//                .and().
//                logout().logoutSuccessUrl("/");
//    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setExposedHeaders(Arrays.asList("X-Auth-Token", "Authorization", "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}