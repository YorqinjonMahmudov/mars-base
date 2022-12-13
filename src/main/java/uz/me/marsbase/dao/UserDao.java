package uz.me.marsbase.dao;

import uz.me.marsbase.model.entity.User;
import uz.me.marsbase.payload.UserDTO;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    boolean makeTeamLead(int userId);

    Optional<User> findByEmail(String email);

    UserDTO isAuthenticated(String email, String password);

}
