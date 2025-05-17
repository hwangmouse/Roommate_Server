package hackathon25.roommate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    /**
     * 발급된 JWT 토큰
     */
    private final String token;
}

