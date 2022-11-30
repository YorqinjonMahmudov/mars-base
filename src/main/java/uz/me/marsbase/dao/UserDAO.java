package uz.me.marsbase.dao;

import uz.me.marsbase.entity.User;

import java.util.Optional;

public interface UserDAO extends Dao<User> {

    boolean updatePassword(int id, String password);

    boolean makeTeamLead(int userId);

    boolean changeFromTeamLeadToUser(int userId);

    Optional<User> findByEmail(String email);

    boolean isAuthenticated(String email, String password);

//    List<User> getPage(int nth, int count, UserRole role, UserStatus status);
}
