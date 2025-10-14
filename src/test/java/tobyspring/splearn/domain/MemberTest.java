package tobyspring.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    @Test
    void createMember() {
        // given
        // when
        var member = new Member("toby@splearn.app", "Toby", "secret");

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }
    
    @Test
    void constructorNullCheck() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new Member(null, "toby", "secret"))
                .isInstanceOf(NullPointerException.class);
    }
    
    @Test
    void activate() {
        // given
        var member = new Member("toby@splearn.app", "Toby", "secret");

        // when
        member.activate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }
    
    @Test
    void activateFail() {
        // given
        var member = new Member("toby@splearn.app", "Toby", "secret");
        member.activate();

        // when
        // then
        assertThatThrownBy(member::activate)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate() {
        // given
        var member = new Member("toby@splearn.app", "Toby", "secret");
        member.activate();

        // when
        member.deactivate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATE);
    }

    @Test
    void deactivateFailIfPendingStatus() {
        // given
        var member = new Member("toby@splearn.app", "Toby", "secret");

        // when
        // then
        assertThatThrownBy(member::deactivate)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivateFailIfDeactivatedStatus() {
        // given
        var member = new Member("toby@splearn.app", "Toby", "secret");
        member.activate();
        member.deactivate();

        // when
        // then
        assertThatThrownBy(member::deactivate)
                .isInstanceOf(IllegalStateException.class);
    }
}