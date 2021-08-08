package com.futbol.services;

import com.futbol.entities.Team;
import com.futbol.exceptions.BusinessException;
import com.futbol.repositories.TeamRepository;
import com.futbol.repositories.entities.TeamEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TeamServiceImplTest {

    @Autowired
    TeamService teamService;
    @MockBean
    TeamRepository repository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTeamOk() throws BusinessException {
        Team team = new Team("", "", "", "", 60000, 1, "2",3, LocalDateTime.now().minusDays(2));

        TeamEntity teamEntity = new TeamEntity("123", "", "", "", 1, 1, "2",3, LocalDateTime.now().minusDays(2));
        Mockito.when(repository.save(Mockito.any(TeamEntity.class))).thenReturn(teamEntity);

        Team response = teamService.createTeam(team);

        assertEquals(teamEntity.getId(), response.getId());
    }

    @Test
    public void ediTeamOk() throws BusinessException {
        Team team = new Team("123", "", "", "", 60000, 1, "2",3, LocalDateTime.now().minusDays(2));

        TeamEntity teamEntity = new TeamEntity("123", "", "", "", 1, 1, "2",3, LocalDateTime.now().minusDays(2));
        Mockito.when(repository.save(Mockito.any(TeamEntity.class))).thenReturn(teamEntity);

        Team response = teamService.editTeam(team);

        assertEquals(teamEntity.getId(), response.getId());
    }
}
