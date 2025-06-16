package it.epicode.challengeu5w3d1.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.epicode.challengeu5w3d1.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTool {

    @Value("${jwt.duration}")
private Long durata;

    @Value("${jwt.secret}")
private String chiaveSegreta;

    public String createToken(User user){



      return   Jwts.builder().issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+durata)).
                subject(user.getId()+"").signWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes())).compact();

    }


    public void validateToken(String token){

        Jwts.parser().verifyWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes())).build().parse(token);
    }
}
