#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class SecurityConfigTest {

    private PasswordEncoder passwordEncoder = new BCryptEncoderConfig().passwordEncoder();

    @Test
    void encryptPassword() {
        String password = "root";
        String encoded = passwordEncoder.encode(password);
        System.out.println(encoded);
    }
}