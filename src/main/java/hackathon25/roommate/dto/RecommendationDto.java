package hackathon25.roommate.dto;

import java.util.List;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RecommendationDto {
    private String nickname;      // 보여줄 유저 이름
    private double similarity;    // 0.0 ~ 1.0 (예: 0.7)
    private String openChatUrl;   // 오픈채팅 연결
}

