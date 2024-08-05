package com.resser.StarChat_AI.Configuration;

import com.resser.StarChat_AI.Entity.User;
import com.resser.StarChat_AI.Service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if(header==null || !header.startsWith("Bearer ")) {


            filterChain.doFilter(request,response);


            return;

        }

        String jwt = header.substring(7);
        String username = jwtService.extractSubject(jwt);
        System.out.println("checking context hodler "+SecurityContextHolder.getContext().getAuthentication());
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails user = userDetailService.loadUserByUsername(username);
            System.out.println("jwtService.isTokenValid(jwt) "+jwtService.isTokenValid(jwt));
            if(user!=null && jwtService.isTokenValid(jwt)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(username,
                                user.getPassword(),
                                user.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new
                        WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(request,response);

    }
}
