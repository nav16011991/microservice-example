package com.nagarro.nagp.userservice.persistent.repository;

import com.nagarro.nagp.userservice.persistent.model.ApplicationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUserEntity, Long> {
}
