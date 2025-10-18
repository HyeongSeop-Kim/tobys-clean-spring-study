package tobyspring.splearn.domain.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProfileTest {
    @Test
    void profile() {
        // given
        // when
        // then
        new Profile("tobyilee");
        new Profile("toby100");
        new Profile("12345");
        new Profile("");
    }

    @Test
    void profileFail() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new Profile("toolongtoolongtoolongtoolong"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Profile("A"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Profile("프로필"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void url() {
        // given
        var profile = new Profile("tobyilee");
        // when
        // then
        assertThat(profile.url()).isEqualTo("@tobyilee");
    }
}