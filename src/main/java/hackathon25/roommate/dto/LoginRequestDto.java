// src/main/java/hackathon25/roommate/dto/LoginRequestDto.java
package hackathon25.roommate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class LoginRequestDto {
    @Email @NotBlank
    private String email;

    @NotBlank
    private String password;
}

