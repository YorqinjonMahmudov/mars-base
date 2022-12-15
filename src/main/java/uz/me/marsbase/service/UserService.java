package uz.me.marsbase.service;

import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.model.entity.User;

import java.util.List;

public interface UserService {

    UserDTO isAuthenticated(String email, String password);

    List<UserDTO> getUsers();

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Integer id);

    boolean insert(User user);

    boolean update(Integer editingUserId, UserDTO userDTO);

    boolean delete(int deletingUserId);

    boolean makeTeamLead(int userId);
}
