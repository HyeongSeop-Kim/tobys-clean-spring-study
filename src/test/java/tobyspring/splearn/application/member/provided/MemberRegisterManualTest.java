//package tobyspring.splearn.application.member.provided;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.test.util.ReflectionTestUtils;
//import tobyspring.splearn.application.MemberService;
//import tobyspring.splearn.application.member.required.EmailSender;
//import tobyspring.splearn.application.member.required.MemberRepository;
//import tobyspring.splearn.domain.shared.Email;
//import tobyspring.splearn.domain.member.Member;
//import tobyspring.splearn.domain.MemberFixture;
//import tobyspring.splearn.domain.member.MemberStatus;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//
//class MemberRegisterManualTest {
//    @Test
//    void registerTestStub() {
//        // given
//        MemberRegister register = new MemberService(
//                new MemberRepositoryStub(),
//                new EmailSenderStub(),
//                MemberFixture.createPasswordEncoder()
//        );
//
//        // when
//        Member member = register.register(MemberFixture.createMemberRegisterRequest());
//
//        // then
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//    }
//
//    @Test
//    void registerTestMock() {
//        // given
//        EmailSenderMock emailSenderMock = new EmailSenderMock();
//
//        MemberRegister register = new MemberService(
//                new MemberRepositoryStub(),
//                emailSenderMock,
//                MemberFixture.createPasswordEncoder()
//        );
//
//        // when
//        Member member = register.register(MemberFixture.createMemberRegisterRequest());
//
//        // then
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//
//        assertThat(emailSenderMock.getTos()).hasSize(1);
//        assertThat(emailSenderMock.getTos().getFirst()).isEqualTo(member.getEmail());
//    }
//
//    @Test
//    void registerTestMockito() {
//        // given
//        EmailSender emailSenderMock = Mockito.mock(EmailSender.class);
//
//        MemberRegister register = new MemberService(
//                new MemberRepositoryStub(),
//                emailSenderMock,
//                MemberFixture.createPasswordEncoder()
//        );
//
//        // when
//        Member member = register.register(MemberFixture.createMemberRegisterRequest());
//
//        // then
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//
//        Mockito.verify(emailSenderMock).send(eq(member.getEmail()), any(), any());
//    }
//
//    static class MemberRepositoryStub implements MemberRepository {
//        @Override
//        public Member save(Member member) {
//            ReflectionTestUtils.setField(member, "id", 1L);
//            return member;
//        }
//
//        @Override
//        public Optional<Member> findByEmail(Email email) {
//            return Optional.empty();
//        }
//    }
//
//    static class EmailSenderStub implements EmailSender {
//        @Override
//        public void send(Email email, String subject, String body) {
//        }
//    }
//
//    static class EmailSenderMock implements EmailSender {
//        List<Email> tos = new ArrayList<>();
//
//        @Override
//        public void send(Email email, String subject, String body) {
//            tos.add(email);
//        }
//
//        public List<Email> getTos() {
//            return tos;
//        }
//    }
//}