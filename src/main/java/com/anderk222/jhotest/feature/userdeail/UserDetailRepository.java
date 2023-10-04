package com.anderk222.jhotest.feature.userdeail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    

    Optional<UserDetail> findByUserId(long id);

}