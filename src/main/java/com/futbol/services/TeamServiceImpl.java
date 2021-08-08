package com.futbol.services;

import com.futbol.entities.Team;
import com.futbol.exceptions.BusinessException;
import com.futbol.repositories.TeamRepository;
import com.futbol.repositories.entities.TeamEntity;
import com.futbol.utils.TeamValidation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    TeamRepository repository;
    ModelMapper mapper;

    @Autowired
    public TeamServiceImpl(TeamRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Team createTeam(Team team) throws BusinessException {
        TeamValidation.validateTeam(team);

        TeamEntity teamEntity = mapper.map(team, TeamEntity.class);

        return this.mapper.map(this.repository.save(teamEntity), Team.class);
    }

    @Override
    public Team editTeam(Team team) throws BusinessException {
        TeamValidation.validateIdTeam(team);
        TeamValidation.validateTeam(team);

        TeamEntity teamEntity = mapper.map(team, TeamEntity.class);
        return this.mapper.map(this.repository.save(teamEntity), Team.class);
    }

    @Override
    public void deleteTeam(String teamId) {
        this.repository.deleteById(teamId);
    }

    @Override
    public Team findById(String teamId) {
        return this.mapper.map(this.repository.findById(teamId), Team.class);
    }

    @Override
    public List<Team> findAll() {
        return this.mapper.map(this.repository.findAll(Sort.by(Sort.Direction.ASC, "capacidadEstadio")), new TypeToken<List<Team>>() {}.getType());
    }
}
