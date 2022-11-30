package uz.me.marsbase.service;

import uz.me.marsbase.dao.UserDAO;
import uz.me.marsbase.dao.imp.UserDAOImpl;
import uz.me.marsbase.entity.User;

public class UserServiceImpl implements UserService {
    private static final UserDAO userDao = UserDAOImpl.getInstance();

    @Override
    public boolean isAuthenticated(String email, String password) {
        return userDao.isAuthenticated(email, password);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }
}
