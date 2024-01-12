package com.example.repository;

import com.example.model.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUid(String uid);

}
