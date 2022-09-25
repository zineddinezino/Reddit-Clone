package com.example.RedditClone.repository;

import com.example.RedditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //@Query(value = "SELECT u FROM User u WHERE u.userName = ?1",nativeQuery = true)
    Optional<User> findByUserName(String userName);
}
