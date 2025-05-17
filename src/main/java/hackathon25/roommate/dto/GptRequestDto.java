package hackathon25.roommate.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GptRequestDto {
    @NotBlank(message = "자기소개 텍스트는 필수입니다")
    private String introduction;
}

