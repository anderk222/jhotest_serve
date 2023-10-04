package com.anderk222.jhotest.feature.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.anderk222.jhotest.exception.ResourceNotFoundException;
import com.anderk222.jhotest.feature.user.domain.User;
import com.anderk222.jhotest.util.Pagination;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Pagination<User> search(String value, int page, int size, Direction direction, String by) {

        Sort sort = Sort.by(direction, by);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> data = repository.findAllByAliasContainingIgnoreCase(value, pageable);

        Pagination<User> res = new Pagination<>(
                page, size, data.getTotalPages(), data.getTotalElements());

        res.setData(data.getContent());

        return res;
    }


    public User find(long id){

        User user = repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException(id, "id", "user"));

        return user;

    }

    public User update(long id, User user){

        this.find(id);

        user.setId(id);

        return repository.save(user);

    }

    public User save(User user){

        user.setId(Long.MIN_VALUE);

        return repository.save(user);

    }

    public User delete(long id){

        User user = this.find(id);

        repository.deleteById(id);

        return user;
    }
    
    public Set<User> findBySharedProject(long projectId){

        Set<User> res = new HashSet<>();

        res.add(repository.findByprojectsId(projectId));

        res.addAll(repository.findBysharedProjectsId(projectId));

        return res;
    }
}