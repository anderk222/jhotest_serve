/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.proyect;

import com.anderk222.jhotest.feature.proyect.domain.ProjectSort;
import com.anderk222.jhotest.feature.proyect.domain.Proyect;
import com.anderk222.jhotest.feature.user.User;
import com.anderk222.jhotest.util.Pagination;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author linuxlite
 */


@Service
public class ProyectService {

    @Autowired
    private ProyectRepository proyectRepository;

    public Proyect findById(long id) {

        Proyect result = proyectRepository.findById(id).orElseThrow(()
                -> new RuntimeException("proyect not found"));

        return result;
    }

    public Pagination<Proyect> findAll(long user, int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);

        Page<Proyect> data = proyectRepository.findByUserId(user, pageable);

        Pagination<Proyect> res = new Pagination(page, limit, data
                .getTotalPages(), data.getTotalElements()
        );
        res.setData(data.getContent());

        return res;

    }

    public Pagination<Proyect> search(String value,int user, int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);

        Page<Proyect> data = proyectRepository.findByNameContainingIgnoreCase(value, pageable);

        Pagination<Proyect> res = new Pagination(page, limit, data
                .getTotalPages(), data.getTotalElements()
        );
        res.setData(data.getContent());

        return res;

    }

    public List<ProjectSort> sort(long user,int page, int limit){
        
        Pageable pageable = PageRequest.of(page, limit);
        
        Page<ProjectSort> data =  proyectRepository.findAllByUserId(user, pageable);
        
        return data.getContent();
        
    }
    
    public Proyect save(Proyect proyect) {
        
        
        proyect.setId(0l);

        return proyectRepository.save(proyect);

    }

    public Proyect update(long id, Proyect proyect) {

        proyectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("proyect not found"));
        
        proyect.setId(id);

        return proyectRepository.save(proyect);

    }
    
    public Proyect delete(long id){
        
        Proyect proyect =  proyectRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("proyect not found"));
        
        proyectRepository.deleteById(id);
        
        return proyect;
        
    }
    
    

}
