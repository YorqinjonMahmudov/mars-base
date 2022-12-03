package uz.me.marsbase.service;

import uz.me.marsbase.model.dto.UserDTO;
import uz.me.marsbase.model.entity.User;

import java.util.List;

public interface UserService {

    UserDTO isAuthenticated(String email, String password);

    List<UserDTO> getUsers();

    UserDTO getUserByEmail(String email);
    UserDTO getUserById(Integer id);

    boolean insert(User user);
}
