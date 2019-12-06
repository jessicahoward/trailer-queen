package org.launchcode.trailerqueen.repository;

import org.launchcode.trailerqueen.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);
}
