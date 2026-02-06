package com.shop.vympel.db.repositories;

import com.shop.vympel.db.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
