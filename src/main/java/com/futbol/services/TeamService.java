package com.futbol.services;

import com.futbol.entities.Team;
import com.futbol.exceptions.BusinessException;

import java.util.List;

public interface TeamService {

    Team createTeam(Team team) throws BusinessException;
    Team editTeam(Team team) throws BusinessException;
    void deleteTeam(String teamId);
    Team findById(String teamId);
    List<Team> findAll();

}
