package hackathon25.roommate.domain;


import hackathon25.roommate.domain.details.*;
import jakarta.persistence.*;
import lombok.*;

/**
 * 한 사용자의 설문 응답 결과
 */
@Entity
@Table(name = "survey_results")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "user"})
public class SurveyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 1:1 매핑, 한 사람당 하나의 설문 결과만 허용 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING) private WakeUpTime wakeUpTime;
    @Enumerated(EnumType.STRING) private CleaningStyle cleaningStyle;
    @Enumerated(EnumType.STRING) private LightTolerance lightTolerance;
    @Enumerated(EnumType.STRING) private SleepingHabit sleepingHabit;
    @Enumerated(EnumType.STRING) private EatingPermission eatingPermission;
    @Enumerated(EnumType.STRING) private HomeReturnCycle homeReturnCycle;
    @Enumerated(EnumType.STRING) private SmokingStatus smokingStatus;
    @Enumerated(EnumType.STRING) private HeatTolerance heatTolerance;
    @Enumerated(EnumType.STRING) private ColdTolerance coldTolerance;
    @Enumerated(EnumType.STRING) private StudyPlace studyPlace;

    /** 설문 다시 보기/수정이 필요할 때 */
    public void updateAnswers(
            WakeUpTime wakeUpTime,
            CleaningStyle cleaningStyle,
            LightTolerance lightTolerance,
            SleepingHabit sleepingHabit,
            EatingPermission eatingPermission,
            HomeReturnCycle homeReturnCycle,
            SmokingStatus smokingStatus,
            HeatTolerance heatTolerance,
            ColdTolerance coldTolerance,
            StudyPlace studyPlace
    ) {
        this.wakeUpTime = wakeUpTime;
        this.cleaningStyle = cleaningStyle;
        this.lightTolerance = lightTolerance;
        this.sleepingHabit = sleepingHabit;
        this.eatingPermission = eatingPermission;
        this.homeReturnCycle = homeReturnCycle;
        this.smokingStatus = smokingStatus;
        this.heatTolerance = heatTolerance;
        this.coldTolerance = coldTolerance;
        this.studyPlace = studyPlace;
    }
}
