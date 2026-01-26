package me.hwanghj.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)          // 기본 생성자(매개변수 없는 생성자)를 자동 생성.
                                                            // PROTECTED 설정으로 동일 패키지 or 상속 관계에서만 호출할 수 있게 제한, JPA같은 프레임워크가 객체를 생성하는 것은 허용.
@Entity
@Getter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 기본키 생성 설정. AUTO_INCREMENT 적용으로 순차적인 번호 할당.
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    // 생성자.
    // 필드의 수가 매우 적고, 모든 필드가 필수이기 때문에 빌더 패턴을 사용하지 않았다고 찾아봄.
    public RefreshToken(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
        return this;        // 객체 자신(RefreshToken)을 return.
    }
}
