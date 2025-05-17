package hackathon25.roommate.dto;

import hackathon25.roommate.domain.details.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SurveyRequestDto {
    @NotNull private WakeUpTime   wakeUpTime;
    @NotNull private CleaningStyle cleaningStyle;
    @NotNull private LightTolerance lightTolerance;
    @NotNull private SleepingHabit  sleepingHabit;
    @NotNull private EatingPermission eatingPermission;
    @NotNull private HomeReturnCycle  homeReturnCycle;
    @NotNull private SmokingStatus    smokingStatus;
    @NotNull private HeatTolerance    heatTolerance;
    @NotNull private ColdTolerance    coldTolerance;
    @NotNull private StudyPlace       studyPlace;
}

