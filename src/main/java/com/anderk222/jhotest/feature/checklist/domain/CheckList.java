/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.checklist.domain;

import com.anderk222.jhotest.feature.checklist.domain.enumerate.Status;
import com.anderk222.jhotest.feature.proyect.domain.Proyect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author linuxlite
 */

@Data
@Entity
public class CheckList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    @Column(length = 200, nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.process;
    
    @Column(columnDefinition = "DATE")
    private LocalDate created = LocalDate.now();
    
    @Column(columnDefinition = "DATE")
    private LocalDate edited;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "check_list")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<CheckListItem> items;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="proyect", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Proyect proyect;
    
}