package com.futbol.repositories;

import com.futbol.repositories.entities.TeamEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<TeamEntity, String> {

}
