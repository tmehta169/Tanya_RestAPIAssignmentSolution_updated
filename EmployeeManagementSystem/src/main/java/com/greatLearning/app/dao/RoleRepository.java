package com.greatLearning.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatLearning.app.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
