package com.cursoHibernate.pruebaHiber.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@ToString @EqualsAndHashCode
public class Usuario {

    @Getter @Setter @Column (name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter @Setter @Column (name = "nombre")
    private String nombre;
    @Getter @Setter @Column (name = "apellido")
    private String apellido;
    @Getter @Setter @Column (name = "email")
    private String email;
    @Getter @Setter @Column (name = "telefono")
    private String telefono;

    @Getter @Setter @Column (name = "password")
    private String password;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }


}
