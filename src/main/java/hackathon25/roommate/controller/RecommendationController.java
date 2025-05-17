package hackathon25.roommate.controller;

import hackathon25.roommate.dto.RecommendationDto;
import hackathon25.roommate.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping
    public List<RecommendationDto> getRecommendations(@RequestParam String email,
                                                      @RequestParam(defaultValue = "3") int topN) {
        return recommendationService.recommendTopN(email, topN);
    }
}

