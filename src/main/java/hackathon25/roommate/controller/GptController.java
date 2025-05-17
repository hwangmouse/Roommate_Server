package hackathon25.roommate.controller;

import hackathon25.roommate.dto.GptRequestDto;
import hackathon25.roommate.dto.GptResponseDto;
import hackathon25.roommate.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/gpt")
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;

    @PostMapping("/analyze")
    public GptResponseDto analyze(@RequestBody GptRequestDto request) {
        String result = gptService.extractKeywordsFromIntroduction(request.getIntroduction());
        return new GptResponseDto(Collections.singletonList(result));
    }
}

