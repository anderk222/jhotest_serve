/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.proyect;

import com.anderk222.jhotest.feature.proyect.domain.ProjectSort;
import com.anderk222.jhotest.feature.proyect.domain.Proyect;
import com.anderk222.jhotest.util.Pagination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linuxlite
 */


@RestController
@RequestMapping("/api/v1/proyect")
@CrossOrigin({"*"})
public class ProyectController {

    @Autowired
    private ProyectService proyectService;

    @GetMapping("/{user}/user")
    public Pagination<Proyect> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "5", required = false) int limit,
            @PathVariable long user
    ) {

        return proyectService.findAll(user, page, limit);

    }
    
    @GetMapping("/{user}/user/sort")
    public List<ProjectSort> sort(
            @PathVariable long user,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "10", required = false) int limit
    ) 
    {
        return proyectService.sort(user, page, limit);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Proyect> findById(@PathVariable long id){
        
        Proyect res = proyectService.findById(id);
        
        return ResponseEntity.ok().body(res);
        
    }

    @GetMapping("/search/{user}/user")
    public Pagination<Proyect> search(
            @PathVariable long user,
            @RequestParam(name="value", defaultValue = "", required = false) String value,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "5", required = false) int limit
    ) 
    {
        return proyectService.search(value, page, page, limit);
    }

    @PostMapping
    public ResponseEntity<Proyect> save(@RequestBody Proyect proyect){
        
        Proyect res =  proyectService.save(proyect);
        
        return ResponseEntity.status(201).body(res);
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Proyect> update(@PathVariable long id, @RequestBody Proyect proyect){
        
        Proyect res = proyectService.update(id, proyect);
        
        return ResponseEntity.ok(res);
        
        
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Proyect> delete(@PathVariable long id){
        
        Proyect res = proyectService.delete(id);
        
        return ResponseEntity.ok(res);
        
    }
    
}
