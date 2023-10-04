/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.anderk222.jhotest.feature.checklist.domain;

import com.anderk222.jhotest.feature.checklist.domain.enumerate.Status;
import java.time.LocalDate;

/**
 *
 * @author linuxlite
 */
public interface CheckListProjection {
    
   public Long getId();
   
   public String getName();
   
   public Status getStatus();
   
   public LocalDate getEdited();
   
    
}
