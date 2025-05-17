package hackathon25.roommate.dto;

import java.util.List;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GptResponseDto {
    /** ChatGPT가 추출한 키워드 목록 */
    private List<String> keywords;
}
