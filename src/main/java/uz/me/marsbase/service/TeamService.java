package uz.me.marsbase.service;

import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.model.dtos.TeamDTO;
import uz.me.marsbase.model.dtos.UserDTO;

import java.util.List;

public interface TeamService {

    List<Team> getTeams();

    List<TeamDTO> getTeamDTOs();

    List<TeamDTO> getTeamDTOsByTeamLeadId(Integer teamLeadId);

    Team getTeamByName(String name);

    Team getTeamById(Integer id);

    boolean insert(Team team);

    boolean update(Integer editingTeamId, Team team);

    boolean delete(int deletingTeamId);

    List<UserDTO> getUsersByTeamId(int teamId);

    boolean insertUserToTeam(int teamId, String user);

    boolean deleteUser(int deletingUserId, int teamId);
}
