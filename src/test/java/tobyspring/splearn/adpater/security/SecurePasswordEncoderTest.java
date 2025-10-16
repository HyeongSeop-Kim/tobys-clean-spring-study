package tobyspring.splearn.adpater.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class SecurePasswordEncoderTest {

    @Test
    void securePasswordEncoder() {
        // given
        SecurePasswordEncoder securePasswordEncoder = new SecurePasswordEncoder();

        // when
        String passwordHash = securePasswordEncoder.encode("secret");

        // then
        assertThat(securePasswordEncoder.matches("secret", passwordHash)).isTrue();
        assertThat(securePasswordEncoder.matches("wrong", passwordHash)).isFalse();
    }
}