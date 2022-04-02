package com.example.springdemo1.repository;

import com.example.springdemo1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value="SELECT u from User u")
    Page<User> findAllUsers(Pageable pageable);

    @Query(value = "SELECT u FROM User u WHERE id=:uid")
    User findUserById(@Param("uid")String uid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM User WHERE id=:uid")
    void deleteUserById(@Param("uid")String uid);
}
