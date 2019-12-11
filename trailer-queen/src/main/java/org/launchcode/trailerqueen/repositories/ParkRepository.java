package org.launchcode.trailerqueen.repositories;

import org.launchcode.trailerqueen.models.Park;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ParkRepository extends CrudRepository<Park, Integer> {
    Park findByCode(int park_code);

}
