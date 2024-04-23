package toy_project.newdy.rest_api.auth.lib;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcCryptoUtils {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // enCoding Password
    public String enCodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    // match password
    public boolean matchPassword(String signinPassword, String password) {
        return bCryptPasswordEncoder.matches(signinPassword, password);
    }

}
