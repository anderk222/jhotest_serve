package com.anderk222.jhotest.feature.user;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anderk222.jhotest.feature.user.domain.User;
import com.anderk222.jhotest.util.Pagination;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/search")
    public Pagination<User> search(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "10", required = false) int limit,
            @RequestParam(name = "value", defaultValue = "", required = false) String value,
            @RequestParam(name = "by", defaultValue = "id", required = false) String by,
            @RequestParam(name = "direction", defaultValue = "DESC", required = false) Direction dir

    ) {

        return service.search(value, page, limit, dir, by);

    }

    @GetMapping("/{id}")
    public User find(@PathVariable long id) {

        return service.find(id);

    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable long id) {

        return service.delete(id);

    }

    @GetMapping("/findBySharedProject/{projectId}")
    public Set<User> findBySharedProject(@PathVariable long projectId){

        return service.findBySharedProject(projectId);

    }

}