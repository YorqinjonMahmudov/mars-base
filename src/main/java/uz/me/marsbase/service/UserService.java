package uz.me.marsbase.service;

import uz.me.marsbase.entity.User;

public interface UserService {

    boolean isAuthenticated(String email, String password);

    User getUserByEmail(String email);
}
