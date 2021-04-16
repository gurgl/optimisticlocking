package com.pilkyoon.repository;

import com.pilkyoon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface UserRepository extends JpaRepository<User, Long> {
    @Lock(LockModeType.NONE)
    @Query("select e from com.pilkyoon.domain.User e where e.id = :id")
    User findByIdWoLock(Long id);

}
