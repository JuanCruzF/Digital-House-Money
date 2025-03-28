package com.juanc.repositories;

import com.juanc.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {
    Optional<Account> getAccountByUserId(Long userId);
}
