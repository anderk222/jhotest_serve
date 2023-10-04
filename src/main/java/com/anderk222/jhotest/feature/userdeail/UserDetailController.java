package com.anderk222.jhotest.feature.userdeail;

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
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/userdetail")
@RestController
@CrossOrigin("*")
public class UserDetailController {

    @Autowired
    private UserDetailService service;

    @GetMapping("/{id}")
    public UserDetail find(@PathVariable long id) {

        return service.find(id);

    }

    @PostMapping("/complete/{userId}/user")
    public ResponseEntity<UserDetail> complete(
            @PathVariable long userId, @RequestBody UserDetail detail) {

        UserDetail _detail = service.completeProfile(userId, detail);
        return ResponseEntity.status(201).body(_detail);
    }

    @PutMapping("/{userId}/user")
    public UserDetail update(@PathVariable long userId, @RequestBody UserDetail detail) {

        return service.update(userId, detail);
    }

    @DeleteMapping("/{id}")
    public UserDetail delete(@PathVariable long id) {

        return service.delete(id);

    }

}