package hackathon25.roommate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SignUpRequestDto {
    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @Email(message = "유효한 이메일 주소여야 합니다")
    @NotBlank(message = "이메일은 필수입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다")
    @Pattern(
            regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,}",
            message = "비밀번호는 최소 8자 이상, 문자와 숫자를 모두 포함해야 합니다"
    )
    private String password;

    @NotBlank(message = "오픈채팅 URL은 필수입니다")
    @Pattern(
            regexp = "https?://.*",
            message = "올바른 URL 형식이어야 합니다"
    )
    private String openChatUrl;
}

