/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.testcase.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import lombok.Data;

/**
 *
 * @author tanki
 */

@Entity
@Data
@Table(name="test_case")
public class TestCase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 200, nullable = false)
    private String name;
    @Lob
    @Column(columnDefinition = "text")
    private String detail;
    
    @Lob
    @Column(columnDefinition = "text")
    private String parameters;
    
    private boolean passed = false;
    
}