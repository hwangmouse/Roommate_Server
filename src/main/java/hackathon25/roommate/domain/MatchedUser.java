package hackathon25.roommate.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 매칭된 사용자 관계 기록
 */
@Entity
@Table(name = "matched_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "user", "matchedWith", "similarity", "matchedAt"})
public class MatchedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 요청한 사용자 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** 매칭된 상대 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matched_with_id", nullable = false)
    private User matchedWith;

    /** ChatGPT 계산 유사도 점수(0.0 ~ 1.0) */
    private double similarity;

    /** 매칭 시각(저장 직전 자동 세팅) */
    private LocalDateTime matchedAt;

    @PrePersist
    private void onPrePersist() {
        this.matchedAt = LocalDateTime.now();
    }

    /** 편리한 정적 팩토리 메서드 */
    public static MatchedUser of(User user, User matchedWith, double similarity) {
        return MatchedUser.builder()
                .user(user)
                .matchedWith(matchedWith)
                .similarity(similarity)
                .build();
    }
}
