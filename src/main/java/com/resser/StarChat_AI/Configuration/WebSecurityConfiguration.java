package com.resser.StarChat_AI.Configuration;

import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration  {

    @Autowired
    private UserDetailService userDetailServiceConfig;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

       return http.authorizeHttpRequests(auth-> {
           auth.requestMatchers("/auth/**").permitAll();
           auth.requestMatchers("/admin/**").hasRole("admin");
            auth.requestMatchers("/user/**").hasRole("user");
            auth.anyRequest().authenticated();

        }).addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();


    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  UserDetailsService userDetailsService() {

        return  userDetailServiceConfig;

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailServiceConfig);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean

    public AuthenticationManager authenticationManager(){

    return  new ProviderManager(authenticationProvider());
    }






}
