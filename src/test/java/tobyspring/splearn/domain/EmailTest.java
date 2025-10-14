package tobyspring.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailTest {
    @Test
    void equality() {
        // given
        var email1 = new Email("toby@splearn.app");
        var email2 = new Email("toby@splearn.app");

        // when
        // then
        assertThat(email1).isEqualTo(email2);
    }

}