package com.talsoft.organizeme.core.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
