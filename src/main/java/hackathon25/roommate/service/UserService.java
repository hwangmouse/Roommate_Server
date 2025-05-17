package hackathon25.roommate.service;

import hackathon25.roommate.domain.User;
import hackathon25.roommate.dto.ProfileResponseDto;
import hackathon25.roommate.dto.ProfileUpdateRequestDto;
import hackathon25.roommate.dto.SignUpRequestDto;
import hackathon25.roommate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /** 프로필 조회 (이메일로 사용자 찾아 introduction, openChatUrl 반환) */
    @Transactional(readOnly = true)
    public ProfileResponseDto getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));

        return new ProfileResponseDto(
                user.getEmail(),
                user.getIntroduction(),   // User 엔티티에 introduction 필드가 있다고 가정
                user.getOpenChatUrl()
        );
    }

    /** 자기소개(introduction) 및 오픈채팅 URL 수정 */
    @Transactional
    public void updateProfile(String email, ProfileUpdateRequestDto dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));

        user.updateIntroduction(dto.getIntroduction());   // User 엔티티에 updateIntroduction 메서드가 있다고 가정
        user.updateOpenChatUrl(dto.getOpenChatUrl());
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByName(String name) {
        return userRepository.existsByNickname(name);
    }

    public Long register(SignUpRequestDto dto) {
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());

        User user = User.builder()
                .email(dto.getEmail())
                .password(encryptedPassword)
                .name(dto.getName())         // ✅ name → nickname
                .gender(0)                        // ✅ 기본값: 미지정 (0)
                .age(0)                           // ✅ 기본값: 미지정 (0)
                .openChatUrl(dto.getOpenChatUrl())
                .build();

        return userRepository.save(user).getId();
    }

}

