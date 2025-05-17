package hackathon25.roommate.repository;

import hackathon25.roommate.domain.MatchedUser;
import hackathon25.roommate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchedUserRepository extends JpaRepository<MatchedUser, Long> {

    /**
     * 특정 사용자가 매칭한 모든 기록 조회
     */
    List<MatchedUser> findAllByUser(User user);

    /**
     * 특정 사용자와 특정 상대 간 매칭 기록 조회
     */
    Optional<MatchedUser> findByUserAndMatchedWith(User user, User matchedWith);

    /**
     * 이미 매칭된 관계인지 확인 (중복 방지용)
     */
    boolean existsByUserAndMatchedWith(User user, User matchedWith);
}

