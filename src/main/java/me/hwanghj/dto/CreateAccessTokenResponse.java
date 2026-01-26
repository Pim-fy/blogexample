package me.hwanghj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor         // 모든 필드를 파라미터로 방는 생성자를 자동 생성.
@Getter
public class CreateAccessTokenResponse {
    private String accessToken;
}
