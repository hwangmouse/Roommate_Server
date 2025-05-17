package hackathon25.roommate.service;

import hackathon25.roommate.domain.SurveyResult;
import hackathon25.roommate.domain.User;
import hackathon25.roommate.dto.RecommendationDto;
import hackathon25.roommate.repository.SurveyRepository;
import hackathon25.roommate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RecommendationService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    /**
     * 추천 기능: 나를 제외한 사람 중에서 유사한 설문 응답자 Top N
     */
    public List<RecommendationDto> recommendTopN(String email, int topN) {
        SurveyResult mySurvey = surveyRepository.findByUserEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("내 설문 응답을 찾을 수 없습니다."));

        List<SurveyResult> others = surveyRepository.findAllByUserEmailNot(email);
        Map<String, Double> similarityMap = new HashMap<>();

        for (SurveyResult other : others) {
            double similarity = calculateSimilarity(mySurvey, other);
            similarityMap.put(other.getUser().getEmail(), similarity);
        }

        return similarityMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(topN)
                .map(entry -> {
                    User user = userRepository.findByEmail(entry.getKey())
                            .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
                    return new RecommendationDto(
                            user.getNickname(),
                            entry.getValue(),
                            user.getOpenChatUrl()
                    );
                })
                .toList();
    }

    /**
     * SurveyResult 기반 유사도 계산: 일치하는 항목 수 / 전체 항목 수
     */
    private double calculateSimilarity(SurveyResult me, SurveyResult other) {
        int total = 10;
        int same = 0;

        if (me.getWakeUpTime() == other.getWakeUpTime()) same++;
        if (me.getCleaningStyle() == other.getCleaningStyle()) same++;
        if (me.getLightTolerance() == other.getLightTolerance()) same++;
        if (me.getSleepingHabit() == other.getSleepingHabit()) same++;
        if (me.getEatingPermission() == other.getEatingPermission()) same++;
        if (me.getHomeReturnCycle() == other.getHomeReturnCycle()) same++;
        if (me.getSmokingStatus() == other.getSmokingStatus()) same++;
        if (me.getHeatTolerance() == other.getHeatTolerance()) same++;
        if (me.getColdTolerance() == other.getColdTolerance()) same++;
        if (me.getStudyPlace() == other.getStudyPlace()) same++;

        return same / (double) total;
    }
}

