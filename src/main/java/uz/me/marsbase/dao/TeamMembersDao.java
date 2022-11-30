package uz.me.marsbase.dao;

import uz.me.marsbase.entity.TeamMember;
import uz.me.marsbase.entity.User;

import java.util.List;

public interface TeamMembersDao extends Dao<TeamMember> {

    List<User> findTeamMembersByTeamId(Integer lotId);

    List<User> findTeamMembersByTeamLeaderId(Integer ownerId);
}
