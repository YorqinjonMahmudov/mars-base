package uz.me.marsbase.service;

import uz.me.marsbase.mappers.UserMapper;
import uz.me.marsbase.model.dao.UserDao;
import uz.me.marsbase.model.dao.imp.UserDaoImpl;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.model.entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final UserMapper userMapper = new UserMapper();

    @Override
    public UserDTO isAuthenticated(String email, String password) {
        return userDao.isAuthenticated(email, password);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userMapper.toDto(userDao.findAll());

    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userMapper.toDto(userDao.findByEmail(email).orElse(null));
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return userMapper.toDto(userDao.findById(id).orElse(null));
    }

    @Override
    public boolean insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public boolean update(Integer editingUserId, UserDTO userDTO) {
        return userDao.update(editingUserId,userMapper.fromDto(userDTO));
    }

    @Override
    public boolean delete(int deletingUserId) {
        return userDao.delete(deletingUserId);
    }

    @Override
    public boolean makeTeamLead(int userId) {
       return userDao.makeTeamLead(userId);
    }
}
