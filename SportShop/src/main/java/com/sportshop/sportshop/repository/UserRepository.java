package com.sportshop.sportshop.repository;

import com.sportshop.sportshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsById(Long id);
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
