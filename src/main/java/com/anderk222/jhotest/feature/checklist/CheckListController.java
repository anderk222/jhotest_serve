/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anderk222.jhotest.feature.checklist;

import com.anderk222.jhotest.feature.checklist.domain.CheckList;
import com.anderk222.jhotest.feature.checklist.domain.CheckListProjection;
import com.anderk222.jhotest.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linuxlite
 */


@RestController
@RequestMapping("/api/v1/check_list")
@CrossOrigin({"*"})
public class CheckListController {

    @Autowired
    private CheckListService checkListService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CheckList> findById(@PathVariable long id) {

        CheckList res = checkListService.findById(id);

        return ResponseEntity.ok(res);

    }

    @GetMapping("/{proyect}/proyect")
    public Pagination<CheckList> findAll(
            @PathVariable long proyect,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "5", required = false) int limit
    ) {

        return checkListService.findAll(proyect, page, limit);

    }

    @GetMapping("/{value}/{proyect}/proyect")
    public Pagination<CheckList> search(
            @PathVariable String value,
            @PathVariable long proyect,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "5", required = false) int limit
    ) {

        return checkListService.search(value, proyect, page, limit);
        
    }
    
    
    @GetMapping("/{project}/project/projection")
    public Pagination<CheckListProjection> projection(
            @PathVariable long project,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "5", required = false) int limit
    ) {

        return checkListService.projection(project, page, limit);
        
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CheckList> save(@RequestBody CheckList check_list){
        
        CheckList res = checkListService.save(check_list);
        
        return ResponseEntity.status(201).body(res);
        
    }
    
    
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CheckList> update(@RequestBody CheckList check_list,
            @PathVariable long id
            ){
        
        CheckList res = checkListService.update(id,check_list);
        
        return ResponseEntity.status(200).body(res);
        
    }
    
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public CheckList delete(@PathVariable long id){
        
        return checkListService.delete(id);
        
    }
}