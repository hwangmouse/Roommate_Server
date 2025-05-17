package hackathon25.roommate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponseDto {
    private final String email;
    private final String introduction;
    private final String openChatUrl;
}



