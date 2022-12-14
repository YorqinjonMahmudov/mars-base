package uz.me.marsbase.service;

import uz.me.marsbase.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.dao.UserDao;
import uz.me.marsbase.dao.imp.UserDaoImpl;
import uz.me.marsbase.mappers.UserMapper;
import uz.me.marsbase.model.entity.User;
import uz.me.marsbase.payload.UserDTO;
import uz.me.marsbase.utils.encoder.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final UserMapper userMapper = new UserMapper();
    private static final PasswordEncoder passwordEncoder = InstanceHolder.getInstance(PasswordEncoder.class);

    @Override
    public UserDTO isAuthenticated(String email, String password) {
        return userDao.isAuthenticated(email, password);
    }

    @Override
    public List<UserDTO> getUsers() {
        var users = userDao.findAll();

        return userMapper.toDto(users
                .stream()
                .peek(userDTO -> userDTO.setPassword(passwordEncoder.decode(userDTO.getPassword())))
                .collect(Collectors.toList()));

    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userMapper.toDto(userDao.findByEmail(email).orElse(null));
    }

    @Override
    public UserDTO getUserById(Integer id) {
        var user = userDao.findById(id).orElse(null);
        if (user != null)
            user.setPassword(passwordEncoder.decode(user.getPassword()));
        return userMapper.toDto(user);
    }

    @Override
    public boolean insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public boolean update(Integer editingUserId, UserDTO userDTO) {
        return userDao.update(editingUserId, userMapper.fromDto(userDTO));
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
