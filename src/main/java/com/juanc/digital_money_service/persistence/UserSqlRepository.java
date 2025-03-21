package com.juanc.digital_money_service.persistence;

import com.juanc.digital_money_service.business.users.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSqlRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByDni(String dni);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
    void changeUserPassword(@Param("password") String password, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.fullName = :fullName, u.dni = :dni, u.email = :email, u.phoneNumber = :phoneNumber WHERE u.id = :id")
    void updateUser(@Param("id") Long id,
                    @Param("fullName") String fullName,
                    @Param("dni") String dni,
                    @Param("email") String email,
                    @Param("phoneNumber") String phoneNumber);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.enabled = TRUE WHERE u.email = ?1")
    void enableAppUser(String email);

    boolean existsByEmail(String email);

    boolean existsByDni(String dni);
}
