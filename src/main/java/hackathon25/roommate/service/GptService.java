package hackathon25.roommate.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class GptService {

    private final WebClient openAiWebClient;

    @Value("${openai.model:gpt-3.5-turbo}")
    private String model;

    public String extractKeywordsFromIntroduction(String introduction) {
        String prompt = buildPrompt(introduction);

        String response = openAiWebClient.post()
                .uri("/v1/chat/completions")
                .header("Authorization", "Bearer " + getApiKey())
                .bodyValue(buildRequest(prompt))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return parseKeywords(response);
    }

    private String buildPrompt(String introduction) {
        return """
                아래 자기소개에서 성격을 나타내는 키워드를 5개 추출해줘. 쉼표(,)로 구분해서 줘.
                
                "%s"
                """.formatted(introduction);
    }

    private String buildRequest(String prompt) {
        return """
        {
          "model": "%s",
          "messages": [
            { "role": "system", "content": "당신은 성격 분석을 해주는 심리학자 AI입니다." },
            { "role": "user", "content": "%s" }
          ],
          "temperature": 0.5
        }
        """.formatted(model, prompt);
    }

    private String getApiKey() {
        return System.getenv("OPENAI_API_KEY");
    }

    private String parseKeywords(String gptResponse) {
        try {
            JSONObject obj = new JSONObject(gptResponse);
            String content = obj.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            return content.trim();
        } catch (Exception e) {
            return "키워드 분석 실패";
        }
    }
}


