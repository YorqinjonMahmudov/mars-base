package uz.me.marsbase.mappers;


import uz.me.marsbase.model.entity.User;
import uz.me.marsbase.payload.UserDTO;

import java.util.List;

public class UserMapper implements BaseMapper<User, UserDTO> {

    private static UserMapper userMapper;

    public static UserMapper getInstance() {
        if (userMapper == null)
            userMapper = new UserMapper();
        return userMapper;
    }

    @Override
    public UserDTO toDto(User user) {
        if (user == null) return null;
        return new UserDTO(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(),
                user.getPassword(),
                user.getRole(), user.getBlockId());
    }

    @Override
    public User fromDto(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRole(), userDTO.getBlockId());
    }

    @Override
    public List<User> fromDto(List<UserDTO> dto) {
        return dto
                .stream()
                .map(this::fromDto)
                .toList();
    }

    @Override
    public List<UserDTO> toDto(List<User> entities) {
        return entities
                .stream()
                .map(this::toDto)
                .toList();
    }

}
