package hackathon25.roommate.repository;

import hackathon25.roommate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일로 사용자 조회 (로그인용)
     */
    Optional<User> findByEmail(String email);

    /**
     * 이메일 중복 체크
     */
    boolean existsByEmail(String email);

    /**
     * 닉네임 중복 체크
     */
    boolean existsByNickname(String nickname);
}
