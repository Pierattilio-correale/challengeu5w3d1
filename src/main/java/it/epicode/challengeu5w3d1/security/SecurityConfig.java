package it.epicode.challengeu5w3d1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//l'annotazione @EnableWebSecurity abilità la classe ad essere
//responsabile della sicurezza dei servizi
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin(http->http.disable());


        httpSecurity.csrf(http->http.disable());

        httpSecurity.sessionManagement(http->http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        httpSecurity.cors(Customizer.withDefaults());


        httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/auth/**").permitAll());
//        httpSecurity.authorizeHttpRequests(http->http.requestMatchers(HttpMethod.GET,"/studenti/**").permitAll());

        httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/dipendenti/**").permitAll());
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/viaggi/**").permitAll());
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/prenotazioni/**").permitAll());
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/users/**").permitAll());
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers(HttpMethod.POST).permitAll());

        httpSecurity.authorizeHttpRequests(http->http.anyRequest().denyAll());

        return httpSecurity.build();
    }
}
