package com.ae.visuavid.repository;

import com.ae.visuavid.domain.Authority;
import com.ae.visuavid.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface RoleRepository extends JpaRepository<Role, String> {}
