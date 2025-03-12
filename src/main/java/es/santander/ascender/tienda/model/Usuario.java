package es.santander.ascender.tienda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; //Esto toca revisarlo

    @Column(nullable = false)
    private String password; //Esto toca revisarlo

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol; // ADMIN o ESTÁNDAR

   
}

