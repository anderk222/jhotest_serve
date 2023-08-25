/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author linuxlite
 */


@Data
@Table(name = "user", schema = "public")
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    @Column(length = 200, unique = true, nullable = false)
    private String alias;
    
    @Column(length = 255, unique = true)
    private String correo;
    
    @Column(length = 255, nullable = false)
    private String fullName;
    
    @Column(length = 12, unique = true)
    private String phone;
    
    
}
