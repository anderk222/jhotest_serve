/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.anderk222.jhotest.feature.proyect;

import com.anderk222.jhotest.feature.proyect.domain.ProjectSort;
import com.anderk222.jhotest.feature.proyect.domain.Proyect;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author linuxlite
 */
public interface ProyectRepository extends JpaRepository<Proyect, Long> {

    @Override
    @Transactional
    public List<Proyect> findAll();

    @Transactional
    public Page<ProjectSort> findAllByUserId(long user,Pageable pageable);

    @Transactional
    public Page<Proyect> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Transactional
    public Page<Proyect> findByUserId(long id, Pageable pageable);
}
