package hackathon25.roommate.dto;

import hackathon25.roommate.domain.details.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SurveyResponseDto {
    private WakeUpTime    wakeUpTime;
    private CleaningStyle cleaningStyle;
    private LightTolerance lightTolerance;
    private SleepingHabit  sleepingHabit;
    private EatingPermission eatingPermission;
    private HomeReturnCycle  homeReturnCycle;
    private SmokingStatus    smokingStatus;
    private HeatTolerance    heatTolerance;
    private ColdTolerance    coldTolerance;
    private StudyPlace       studyPlace;
}

