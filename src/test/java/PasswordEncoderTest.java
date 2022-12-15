import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uz.me.marsbase.controller.command.instanceHolder.InstanceHolder;
import uz.me.marsbase.utils.encoder.PasswordEncoder;

public final class PasswordEncoderTest {
    private PasswordEncoder passwordEncoder = InstanceHolder.getInstance(PasswordEncoder.class);


    @Test
    public void encodePasswordHappyTest() {
        String password = "root123";
        String encode = passwordEncoder.encode(password);
        Assertions.assertEquals(password, passwordEncoder.decode(encode));
    }

    @Test
    public void encodePasswordUnhappyTest() {
        String password = "root123";
        String encode = passwordEncoder.encode(password);
        Assertions.assertNotEquals(password+"1", passwordEncoder.decode(encode));

    }


}
