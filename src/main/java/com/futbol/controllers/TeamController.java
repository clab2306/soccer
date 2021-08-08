package com.futbol.controllers;

import com.futbol.entities.Team;
import com.futbol.exceptions.BusinessException;
import com.futbol.routers.Router;
import com.futbol.services.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = Router.TEAM)
public class TeamController {

    Logger logger = LoggerFactory.getLogger(TeamController.class);
    TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    public Team createTeam(HttpServletRequest request, @RequestBody Team team) throws BusinessException {
        logger.info("create team request {}", team);
        return this.teamService.createTeam(team);
    }

    @PutMapping()
    public Team editTeam(HttpServletRequest request, @RequestBody Team team) throws BusinessException {
        logger.info("edit team request {}", team);
        return this.teamService.editTeam(team);
    }

    @GetMapping()
    public ResponseEntity<List<Team>> findAll(HttpServletRequest request) {
        logger.info("find all teams request");
        List<Team> teams = this.teamService.findAll();
        if (teams.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> findById(HttpServletRequest request, @PathParam("teamId") String teamId) {
        logger.info("find by Id team request {}", teamId);
        Team team = this.teamService.findById(teamId);
        if (team == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(team);
    }

    @DeleteMapping("/{teamId}")
    public void deleteById(HttpServletRequest request, @PathParam("teamId") String teamId) {
        logger.info("delete by Id team request {}", teamId);
        this.teamService.deleteTeam(teamId);
    }
}
