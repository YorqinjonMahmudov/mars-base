import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.me.marsbase.model.dtos.UserDTO;
import uz.me.marsbase.model.entity.enums.Role;
import uz.me.marsbase.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    UserService userService;

    @BeforeEach
    public void setup() {

        lenient().when(userService.isAuthenticated("admin@gmail.com", "root123"))
                .thenReturn(new UserDTO(1, "admin", "", "admin@gmail.com", "root123", Role.ADMIN, 1));

        lenient().when(userService.delete(1)).thenReturn(true);

        lenient().when(userService.getUserByEmail("john@gmail.com"))
                .thenReturn(new UserDTO(2, "John", "Hopkins", "john@gmail.com", "john123", Role.TEAM_LEADER, 1));


    }

    @Test
    void deleteUserHappyTest() {
        boolean delete = userService.delete(1);
        assertTrue(delete);
    }

    @Test
    void isAuthenticatedHappyTest() {
        UserDTO userDTO = userService.isAuthenticated("admin@gmail.com", "root123");

        assertEquals(userDTO.getRole(), Role.ADMIN);
    }

    @Test
    void getUserByEmailHappyTest() {
        UserDTO userDTO = userService.getUserByEmail("john@gmail.com");

        assertEquals(userDTO.getPassword(), "john123");
    }
}
