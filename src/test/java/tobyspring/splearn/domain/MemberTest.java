package tobyspring.splearn.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
        member = Member.create(new MemberCreateRequest("toby@splearn.app", "Toby", "secret"), passwordEncoder);
    }

    @Test
    void createMember() {
        // given
        // when
        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }
    
    @Test
    void activate() {
        // given
        // when
        member.activate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }
    
    @Test
    void activateFail() {
        // given
        member.activate();

        // when
        // then
        assertThatThrownBy(member::activate)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate() {
        // given
        member.activate();

        // when
        member.deactivate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATE);
    }

    @Test
    void deactivateFailIfPendingStatus() {
        // given
        // when
        // then
        assertThatThrownBy(member::deactivate)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivateFailIfDeactivatedStatus() {
        // given
        member.activate();
        member.deactivate();

        // when
        // then
        assertThatThrownBy(member::deactivate)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void verifyPassword() {
        // given
        // when
        // then
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("hello", passwordEncoder)).isFalse();
    }

    @Test
    void changeNickname() {
        // given
        assertThat(member.getNickname()).isEqualTo("Toby");

        // when
        member.changeNickname("Charlie");

        // then
        assertThat(member.getNickname()).isEqualTo("Charlie");
    }
    
    @Test
    void changePassword() {
        // given
        // when
        member.changePassword("verysecret", passwordEncoder);
    
        // then
        assertThat(member.verifyPassword("verysecret", passwordEncoder)).isTrue();
    }

    @Test
    void isActive() {
        // given
        // when
        // then
        assertThat(member.isActive()).isFalse();

        member.activate();

        assertThat(member.isActive()).isTrue();

        member.deactivate();

        assertThat(member.isActive()).isFalse();
    }

    @Test
    void invalidEmail() {
        // given
        // when
        // then
        assertThatThrownBy(() ->
                Member.create(new MemberCreateRequest("invalid email", "Toby", "secret"), passwordEncoder)
        ).isInstanceOf(IllegalArgumentException.class);

        Member.create(new MemberCreateRequest("tobyilee@gmail.com", "Toby", "secret"), passwordEncoder);
    }
}