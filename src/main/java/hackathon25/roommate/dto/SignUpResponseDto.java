package hackathon25.roommate.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SignUpResponseDto {
    private Long id;
    private String name;
    private String email;
    private String openChatUrl;
}
