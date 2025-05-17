package hackathon25.roommate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ProfileUpdateRequestDto {
    @NotBlank(message = "자기소개는 필수입니다")
    private String introduction;

    @NotBlank(message = "오픈채팅 URL은 필수입니다")
    @Pattern(regexp = "https?://.*", message = "올바른 URL 형식이어야 합니다")
    private String openChatUrl;
}

