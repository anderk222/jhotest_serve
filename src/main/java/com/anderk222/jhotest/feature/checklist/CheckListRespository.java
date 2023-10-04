/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.anderk222.jhotest.feature.checklist;

import com.anderk222.jhotest.feature.checklist.domain.CheckList;
import com.anderk222.jhotest.feature.checklist.domain.CheckListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author linuxlite
 */


public interface CheckListRespository extends JpaRepository<CheckList, Long>{
    
    
    Page<CheckList> findByProyectId(Long id,Pageable pageable);
    
    Page<CheckList> findByNameContainingIgnoreCaseAndProyectId(
            String name, Long proyect, Pageable pageable
    );
    
    Page<CheckListProjection> findAllByProyectId(Long project, Pageable page);

    
    
}
