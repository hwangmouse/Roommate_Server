package hackathon25.roommate.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKeyBase64;

    @Value("${jwt.expiration-ms}")
    private long validityInMs;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        // Base64로 인코딩된 시크릿 키를 디코딩해서 SecretKey 객체 생성
        byte[] keyBytes = io.jsonwebtoken.io.Decoders.BASE64.decode(secretKeyBase64);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 주어진 이메일(email)을 subject로 하는 JWT 토큰 생성
     */
    public String createToken(String email) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    /**
     * 토큰에서 사용자 식별 정보(email)를 꺼낸다
     */
    public String getEmail(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

    /**
     * 토큰 유효성 검증 (서명 & 만료시간)
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}


