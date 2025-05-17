package hackathon25.roommate.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 사용자 계정 정보 (스프링 시큐리티용 UserDetails 구현체)
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "email", "name"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 로그인 아이디(이메일) */
    @Column(nullable = false, unique = true)
    private String email;

    /** 암호화된 비밀번호 */
    @Column(nullable = false)
    private String password;

    /** 사용자 표시명(별명) */
    @Column(nullable = false, unique = true)
    private String name;

    /** 성별 (0: 미지정, 1: 남, 2: 여 등) */
    @Column(nullable = false)
    private int gender;

    /** 나이 */
    @Column(nullable = false)
    private int age;

    /** 오픈챗 URL */
    @Column(nullable = false)
    private String openChatUrl;

    @Column(nullable = false)
    private String introduction;

    // ——————————————————————————————————————————————
    // 도메인 로직
    // ——————————————————————————————————————————————
    /** 자기소개 업데이트*/
    public void updateIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /** 비밀번호 변경 */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    /** 프로필(별명/성별/나이) 업데이트 */
    public void updateProfile(String nickname, int gender, int age) {
        this.nickname = nickname;
        this.gender   = gender;
        this.age      = age;
    }

    /** 오픈챗 URL만 별도로 업데이트 */
    public void updateOpenChatUrl(String openChatUrl) {
        this.openChatUrl = openChatUrl;
    }

    // ——————————————————————————————————————————————
    // UserDetails 인터페이스 구현
    // ——————————————————————————————————————————————

    /**
     * 인증된 사용자의 권한 목록을 반환합니다.
     * 여기서는 단일 ROLE_USER 권한만 부여하되, 필요하면 컬럼을 추가해서 저장된 권한을 꺼내오면 됩니다.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /** 로그인 아이디로 사용할 유저 이름을 반환 (여기서는 email) */
    @Override
    public String getUsername() {
        return this.email;
    }

    /** 로그인 시 비교할 비밀번호를 반환 (암호화된 상태여야 함) */
    @Override
    public String getPassword() {
        return this.password;
    }

    /** 계정 만료 여부 (true: 만료 안 됨) */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /** 계정 잠금 여부 (true: 잠기지 않음) */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** 자격 증명(비밀번호) 만료 여부 (true: 만료 안 됨) */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** 사용자 활성화 여부 (true: 활성화) */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

