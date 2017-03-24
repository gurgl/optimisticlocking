package com.pilkyoon.repository;

import com.pilkyoon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface UserRepository extends JpaRepository<User, Long> {
    @Lock(LockModeType.NONE)
    User findById(Long id);

}
