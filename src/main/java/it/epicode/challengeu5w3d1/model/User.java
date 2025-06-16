package it.epicode.challengeu5w3d1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
   private String username;
    private String nome;
   private  String cognome;
    @Column(unique = true)
    private String email;
    private String password;
}
