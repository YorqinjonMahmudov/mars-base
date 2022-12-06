package uz.me.marsbase.service;

import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.model.dao.TeamDao;
import uz.me.marsbase.model.dao.imp.TeamDAOImpl;
import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.payload.TeamDTO;

import java.util.List;


public class TeamServiceImpl implements TeamService {
    private final TeamDao teamDao = TeamDAOImpl.getInstance();
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    @Override
    public List<Team> getTeams() {
        var teamList = teamDao.findAll();
        return teamList;
    }

    @Override
    public List<TeamDTO> getTeamDTOs() {
        var teams = getTeams();
        var teamDTOList = teams.stream().map(
                team ->
                        new TeamDTO(team.getId(),
                                team.getName(),
                                userService.getUserById(team.getTeamLeadId()).getEmail(),
                                team.isActive()
                        )
        ).toList();

        return teamDTOList;
    }

    @Override
    public Team getTeamByName(String name) {
        var optionalTeam = teamDao.findByName(name);
        return optionalTeam.orElse(null);
    }

    @Override
    public Team getTeamById(Integer id) {
        var optionalTeam = teamDao.findById(id);
        return optionalTeam.orElse(null);
    }

    @Override
    public boolean insert(Team team) {
        return teamDao.insert(team);
    }

    @Override
    public boolean update(Integer editingTeamId, Team team) {
        return teamDao.update(editingTeamId, team);
    }

    @Override
    public boolean delete(int deletingTeamId) {
        return teamDao.delete(deletingTeamId);
    }
}
