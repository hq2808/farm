package com.ride_sharing.ridesharing.repository;

import com.ride_sharing.ridesharing.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByIsActive(Boolean isActive);

    @Query("SELECT u FROM User u ORDER BY u.lastLogin DESC")
    Page<User> findTopActiveUsers(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.lastLogin = CURRENT_TIMESTAMP WHERE u.email = :email")
    void updateLastLoginByEmail(@Param("email") String email);
}