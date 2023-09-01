/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.proyect.domain;

import com.anderk222.jhotest.feature.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author linuxlite
 */

@Data
@Entity
public class Proyect {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 200, nullable = false)
    private String name; 
    
    @Lob
    @Column(columnDefinition = "text")
    private String img; 
    
    @Lob
    @Column(columnDefinition = "text")
    private String detail;
    
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn(referencedColumnName = "id", name = "\"user\"")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    
}
