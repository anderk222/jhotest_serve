/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.checklist;

import com.anderk222.jhotest.feature.checklist.domain.CheckList;
import com.anderk222.jhotest.feature.checklist.domain.CheckListProjection;
import com.anderk222.jhotest.feature.proyect.ProyectService;
import com.anderk222.jhotest.feature.proyect.domain.Proyect;
import com.anderk222.jhotest.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author linuxlite
 */
@Service
public class CheckListService {

    @Autowired
    private CheckListRespository checkListRepository;
    @Autowired
    private ProyectService proyectService;

    public Pagination<CheckList> findAll(long proyect, int page, int limit) {

        Proyect _proyect = proyectService.findById(proyect);

        Pageable pageable = PageRequest.of(page, limit);

        Page<CheckList> data = checkListRepository.findByProyectId(proyect, pageable);

        Pagination<CheckList> res = new Pagination(page, limit,
                data.getTotalPages(), data.getTotalElements()
        );

        res.setData(data.getContent());

        return res;

    }
    
    public Pagination<CheckListProjection> projection(long proyect, int page, int limit){
        
        Pageable pageable = PageRequest.of(page,limit);
        
        Page<CheckListProjection> data = checkListRepository
                .findAllByProyectId(proyect, pageable);
        
        Pagination<CheckListProjection> res = new Pagination(page, limit,
                data.getTotalPages(), data.getTotalElements()
        );
        
        res.setData(data.getContent());

        return res;        
    }

    public CheckList findById(long id) {

        CheckList check_list = checkListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("checkListNotFound"));

        return check_list;

    }

    public Pagination<CheckList> search(String value, long proyect, int page, int limit) {

        Proyect _proyect = proyectService.findById(proyect);

        Pageable pageable = PageRequest.of(page, limit);

        Page<CheckList> data = checkListRepository
                .findByNameContainingIgnoreCaseAndProyectId(value, proyect, pageable);
        Pagination<CheckList> res = new Pagination(page, limit,
                data.getTotalPages(), data.getTotalElements()
        );

        res.setData(data.getContent());

        return res;

    }

    public CheckList save(CheckList check_list) {

        Proyect proyect = proyectService.findById(check_list.getProyect().getId());

        check_list.setId(0l);

        return checkListRepository.save(check_list);

    }

    public CheckList update(long id, CheckList check_list) {

        System.out.println(check_list);
        
        CheckList updated = this.findById(id);

        updated.setName(check_list.getName());
        updated.setItems(check_list.getItems());
        updated.setStatus(check_list.getStatus());

        return checkListRepository.save(updated);

    }

    public CheckList delete(long id) {

        CheckList check_list = this.findById(id);

        checkListRepository.deleteById(id);

        return check_list;

    }

}
