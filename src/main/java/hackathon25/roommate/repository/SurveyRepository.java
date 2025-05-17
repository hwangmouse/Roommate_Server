package hackathon25.roommate.repository;

import hackathon25.roommate.domain.SurveyResult;
import hackathon25.roommate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<SurveyResult, Long> {

    /**
     * 특정 사용자의 설문 결과 조회
     */
    Optional<SurveyResult> findByUser(User user);

    /**
     * 사용자가 이미 설문을 제출했는지 확인
     */
    boolean existsByUser(User user);

    /**
     * 설문 재제출 시 기존 결과 삭제
     */
    @Modifying
    @Transactional
    void deleteByUser(User user);
    Optional<SurveyResult> findByUserEmail(String email);
    List<SurveyResult> findAllByUserEmailNot(String email);

}

