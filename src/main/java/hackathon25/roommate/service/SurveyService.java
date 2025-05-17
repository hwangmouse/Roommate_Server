package hackathon25.roommate.service;

import hackathon25.roommate.domain.SurveyResult;
import hackathon25.roommate.domain.User;
import hackathon25.roommate.repository.SurveyRepository;
import hackathon25.roommate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    public void saveSurvey(String userEmail, SurveyResult newSurvey) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Optional<SurveyResult> existing = surveyRepository.findByUser(user);
        existing.ifPresent(surveyRepository::delete);

        newSurvey.setUser(user); // 연관관계 설정
        surveyRepository.save(newSurvey);
    }

    public SurveyResult getSurvey(String email) {
        return surveyRepository.findByUserEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("설문 결과가 없습니다."));
    }
}

