package uz.me.marsbase.service;

import uz.me.marsbase.model.entity.Team;
import uz.me.marsbase.payload.TeamDTO;
import uz.me.marsbase.payload.UserDTO;

import java.util.List;

public interface TeamService {

    List<Team> getTeams();

    List<TeamDTO> getTeamDTOs();

    Team getTeamByName(String name);

    Team getTeamById(Integer id);

    boolean insert(Team team);

    boolean update(Integer editingTeamId, Team team);

    boolean delete(int deletingTeamId);

    List<UserDTO> getUsersByTeamId(int teamId);

    boolean insertUserToTeam(int teamId, String user);

    boolean deleteUser(int deletingUserId,int teamId);
}
