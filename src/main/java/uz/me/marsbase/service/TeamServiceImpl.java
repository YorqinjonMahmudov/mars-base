package uz.me.marsbase.service;

import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.mappers.UserMapper;
import uz.me.marsbase.dao.TeamDao;
import uz.me.marsbase.dao.TeamMembersDao;
import uz.me.marsbase.dao.imp.TeamDAOImpl;
import uz.me.marsbase.dao.imp.TeamMembersDAOImpl;
import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.model.entity.TeamMember;
import uz.me.marsbase.model.entity.User;
import uz.me.marsbase.payload.TeamDTO;
import uz.me.marsbase.payload.UserDTO;

import java.util.List;


public class TeamServiceImpl implements TeamService {
    private final TeamDao teamDao = TeamDAOImpl.getInstance();
    private final TeamMembersDao teamMembersDao = TeamMembersDAOImpl.getInstance();
    private final UserService userService = InstanceHolder.getInstance(UserService.class);

    private final UserMapper userMapper = UserMapper.getInstance();


    @Override
    public List<Team> getTeams() {
        return teamDao.findAll();
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

    @Override
    public List<UserDTO> getUsersByTeamId(int teamId) {
        List<User> teamMembers = teamMembersDao.findTeamMembersByTeamId(teamId);
        return userMapper.toDto(teamMembers);

    }

    @Override
    public boolean insertUserToTeam(int teamId, String email) {
        UserDTO userByEmail = userService.getUserByEmail(email);
        if (teamDao.findById(teamId).isPresent() && userByEmail != null)
            return teamMembersDao.insert(new TeamMember(userByEmail.getId(), teamId));
        else return false;
    }

    @Override
    public boolean deleteUser(int deletingUserId, int teamId) {
        return teamMembersDao.deleteIUser(deletingUserId, teamId);
    }
}
