package me.hwanghj.service;

import lombok.RequiredArgsConstructor;
import me.hwanghj.domain.User;
import me.hwanghj.dto.AddUserRequest;
import me.hwanghj.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor        // final 필드 생성자 자동 생성.
@Service
public class UserService {
    // 생성자 주입.
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

// 9장 학습중 코드 변경으로 주석처리.
//    public Long save(AddUserRequest dto) {
//        return userRepository
//                .save(
//                    User.builder()
//                        .email(dto.getEmail())
//                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
//                        .build())
//                .getId();
//    }
    public Long save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(
                User
                        .builder()
                        .email(dto.getEmail())
                        .password(encoder.encode(dto.getPassword()))
                        .build()
                ).getId();
    }

    public User findById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    // OAuth2에서 제공하는 이메일은 유일값.
    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
