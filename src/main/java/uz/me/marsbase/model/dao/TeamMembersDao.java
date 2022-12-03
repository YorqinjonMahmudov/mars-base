package uz.me.marsbase.model.dao;

import uz.me.marsbase.model.entity.TeamMember;
import uz.me.marsbase.model.entity.User;

import java.util.List;

public interface TeamMembersDao extends Dao<TeamMember> {

    List<User> findTeamMembersByTeamId(Integer lotId);

    List<User> findTeamMembersByTeamLeaderId(Integer ownerId);
}
