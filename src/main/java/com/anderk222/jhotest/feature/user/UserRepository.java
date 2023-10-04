package com.anderk222.jhotest.feature.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.anderk222.jhotest.feature.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
    Page<User> findAllByAliasContainingIgnoreCase(String alias, Pageable pageable);

    List<User> findBysharedProjectsId(long projectId);

    User findByprojectsId(long projectId);
    
}