package org.launchcode.trailerqueen.repository;

import org.launchcode.trailerqueen.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Role, Integer> {

    public Role findByRole(String role);
}
