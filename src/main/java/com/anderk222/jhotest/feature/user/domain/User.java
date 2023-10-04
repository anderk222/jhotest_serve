/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.user.domain;

import java.util.Set;

import com.anderk222.jhotest.feature.proyect.domain.Proyect;
import com.anderk222.jhotest.feature.userdeail.UserDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author linuxlite
 */

@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, unique = true, nullable = false)
    private String alias;

    @Column(length = 255, unique = true)
    private String correo;

    @Column(columnDefinition = "text", nullable = false)
    @JsonIgnore
    private String password;

    @OneToOne(mappedBy = "user")
    @JsonProperty(access = Access.READ_ONLY)
    private UserDetail detail;

    @JsonIgnore
    @ManyToMany(mappedBy = "sharedUsers")
    private Set<Proyect> sharedProjects;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Proyect> projects;
}