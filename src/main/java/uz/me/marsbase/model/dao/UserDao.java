package uz.me.marsbase.model.dao;

import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.model.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    boolean updatePassword(int id, String password);

    boolean makeTeamLead(int userId);

    boolean changeFromTeamLeadToUser(int userId);

    Optional<User> findByEmail(String email);

    UserDTO isAuthenticated(String email, String password);

//    List<User> getPage(int nth, int count, UserRole role, UserStatus status);
}
