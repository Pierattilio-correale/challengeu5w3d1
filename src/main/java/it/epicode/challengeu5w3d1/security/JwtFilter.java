package it.epicode.challengeu5w3d1.security;


import it.epicode.challengeu5w3d1.exception.UnauthoraziedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component



public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTool jwtTool;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization =request.getHeader("Authorization");


        if(authorization==null || !authorization.startsWith("Bearer ")){
            throw new UnauthoraziedException("Token non presente , non sei autorizzato ad usare il servizio richiesto");

        }else{

            String token= authorization.substring(7);



            jwtTool.validateToken(token);

            filterChain.doFilter(request,response);

        }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
