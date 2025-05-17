package hackathon25.roommate.controller;

import hackathon25.roommate.domain.SurveyResult;
import hackathon25.roommate.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public void submitSurvey(@RequestParam String email,
                             @RequestBody SurveyResult result) {
        surveyService.saveSurvey(email, result);
    }

    @GetMapping
    public SurveyResult getSurvey(@RequestParam String email) {
        return surveyService.getSurvey(email);
    }
}

