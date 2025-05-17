package hackathon25.roommate.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * GPT 분석 결과를 저장한 프로필 엔티티
 */
@Entity
@Table(name = "personality_profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "user"})
public class PersonalityProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 1:1 매핑, 한 사람당 하나의 프로필만 허용 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    /** GPT가 반환한 JSON 문자열(키워드, 점수 등) */
    @Column(columnDefinition = "TEXT")
    private String profileJson;

    /** 새로 분석했을 때 업데이트 */
    public void updateProfileJson(String profileJson) {
        this.profileJson = profileJson;
    }
}
