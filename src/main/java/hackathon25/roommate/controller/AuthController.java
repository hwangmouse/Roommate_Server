package hackathon25.roommate.controller;

import hackathon25.roommate.config.JwtTokenProvider;
import hackathon25.roommate.dto.*;
import hackathon25.roommate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;
    private final UserService userService;

    // 🔐 로그인
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
            authManager.authenticate(authToken);

            String token = jwtProvider.createToken(dto.getEmail());
            return new LoginResponseDto(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid login credentials");
        }
    }

    // 📝 회원가입
    @PostMapping("/signup")
    public SignUpResponseDto signup(@RequestBody @Valid SignUpRequestDto dto) {
        if (userService.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        if (userService.existsByName(dto.getName())) {
            throw new IllegalArgumentException("이미 사용 중인 이름입니다.");
        }

        Long id = userService.register(dto);

        return SignUpResponseDto.builder()
                .id(id)
                .name(dto.getName())
                .email(dto.getEmail())
                .openChatUrl(dto.getOpenChatUrl())
                .build();
    }
}

