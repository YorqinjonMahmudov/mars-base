package uz.me.marsbase.utils.encoder;

import java.util.Base64;

/**
 * Custom password encoder
 */

public final class PasswordEncoder {


    /**
     * Encode password.
     *
     * @param password Password to encrypt.
     * @return Encrypted password.
     */
    public String encode(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    /**
     * Encode password.
     *
     * @param password Password to decrypt.
     * @return Decrypted password.
     */
    public String decode(String password) {
        return new String(Base64.getDecoder().decode(password));
    }
}
