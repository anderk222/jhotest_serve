package com.anderk222.jhotest.feature.userdeail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anderk222.jhotest.exception.ResourceNotFoundException;
import com.anderk222.jhotest.feature.user.UserService;
import com.anderk222.jhotest.feature.user.domain.User;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailRepository repository;

    @Autowired
    private UserService userService;

    public UserDetail find(long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "user"));

    }

    public UserDetail completeProfile(long userId, UserDetail detail) {

        User user = userService.find(userId);

        detail.setUser(user);

        detail.setId(Long.MIN_VALUE);

        return repository.save(detail);

    }

    public UserDetail update(long userId, UserDetail detail) {

        UserDetail _detail = this.findByUser(userId);
        detail.setId(_detail.getId());
        User user = _detail.getUser();

        detail.setUser(user);

        return repository.save(detail);

    }

    public UserDetail delete(long id){

        UserDetail detail = this.find(id);

        repository.deleteById(id);

        return detail;

    }

    private UserDetail findByUser(long userId) {

        return repository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId, "userId", "userDetail"));
    }
}