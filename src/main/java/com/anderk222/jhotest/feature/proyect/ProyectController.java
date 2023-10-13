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
@CrossOrigin({ "*" })
public class ProyectController {

    @Autowired
    private ProyectService service;

    @GetMapping("/{user}/user")
    public Pagination<Proyect> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "5", required = false) int limit,
            @PathVariable Long user) {

        return service.findAll(user, page, limit);

    }

    @GetMapping("/{user}/user/sort")
    public List<ProjectSort> sort(
            @PathVariable Long user,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "10", required = false) int limit) {
        return service.sort(user, page, limit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyect> findById(@PathVariable Long id) {

        Proyect res = service.findById(id);

        return ResponseEntity.ok().body(res);

    }

    @GetMapping("/search/{user}/user")
    public Pagination<Proyect> search(
            @PathVariable Long user,
            @RequestParam(name = "value", defaultValue = "", required = false) String value,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "5", required = false) int limit) {
        return service.search(value, user, page, limit);
    }

    @PostMapping
    public ResponseEntity<Proyect> save(@RequestBody Proyect proyect) {

        Proyect res = service.save(proyect);

        return ResponseEntity.status(201).body(res);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyect> update(@PathVariable Long id, @RequestBody Proyect proyect) {

        Proyect res = service.update(id, proyect);

        return ResponseEntity.ok(res);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Proyect> delete(@PathVariable Long id) {

        Proyect res = service.delete(id);

        return ResponseEntity.ok(res);

    }

    @PostMapping("/adduser/{id}/{userId}/user")
    public Proyect addUser(@PathVariable long id, @PathVariable long userId) {

        return service.addUser(id, userId);

    }

    @GetMapping("/findBySharedUser/{userId}")
    public Pagination<Proyect> findBySharedUser(
            @PathVariable long userId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "5", required = false) int limit

    ) {

        return service.findBySharedUser(userId, page, limit);

    }
}