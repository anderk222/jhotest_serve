/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.proyect;

import com.anderk222.jhotest.feature.proyect.domain.ProjectSort;
import com.anderk222.jhotest.feature.proyect.domain.Proyect;
import com.anderk222.jhotest.feature.user.UserService;
import com.anderk222.jhotest.feature.user.domain.User;
import com.anderk222.jhotest.util.Pagination;
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
    private ProyectRepository repository;

    @Autowired
    private UserService userService;

    public Proyect findById(Long id) {

        Proyect result = repository.findById(id).orElseThrow(() -> new RuntimeException("proyect not found"));

        return result;
    }

    public Pagination<Proyect> findAll(Long user, int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);

        Page<Proyect> data = repository.findByUserId(user, pageable);

        Pagination<Proyect> res = new Pagination<>(page, limit, data
                .getTotalPages(), data.getTotalElements());
        res.setData(data.getContent());

        return res;

    }

    public Pagination<Proyect> search(String value, long userId, int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);

        Page<Proyect> data = repository.findByNameContainingIgnoreCaseAndUserId(value,userId, pageable);

        Pagination<Proyect> res = new Pagination<>(page, limit, data
                .getTotalPages(), data.getTotalElements());
        res.setData(data.getContent());

        return res;

    }

    public List<ProjectSort> sort(Long user, int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);

        Page<ProjectSort> data = repository.findAllByUserId(user, pageable);

        return data.getContent();

    }

    public Proyect save(Proyect proyect) {

        proyect.setId(0l);

        return repository.save(proyect);

    }

    public Proyect update(Long id, Proyect proyect) {

        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("proyect not found"));

        proyect.setId(id);

        return repository.save(proyect);

    }

    public Proyect delete(Long id) {

        Proyect proyect = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("proyect not found"));

        repository.deleteById(id);

        return proyect;

    }

    public Proyect addUser(long proyectId, long userId) {

        Proyect proyect = this.findById(proyectId);

        User user = userService.find(userId);

        proyect.addUser(user);

        return repository.save(proyect);

    }

    public Pagination<Proyect> findBySharedUser(long userId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Proyect> data = repository.findBysharedUsersId(userId, pageable);

        Pagination<Proyect> res = new Pagination<>(page, size, data.getTotalPages(), data.getTotalElements());

        res.setData(data.getContent());

        return res;
    }

}