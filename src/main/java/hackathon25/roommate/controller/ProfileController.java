package hackathon25.roommate.controller;

import hackathon25.roommate.dto.ProfileResponseDto;
import hackathon25.roommate.dto.ProfileUpdateRequestDto;
import hackathon25.roommate.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/me")
    public ProfileResponseDto getMyProfile(@RequestParam String email) {
        return userService.getProfile(email);
    }

    @PutMapping("/me")
    public void updateProfile(@RequestParam String email,
                              @RequestBody @Valid ProfileUpdateRequestDto dto) {
        userService.updateProfile(email, dto);
    }
}

