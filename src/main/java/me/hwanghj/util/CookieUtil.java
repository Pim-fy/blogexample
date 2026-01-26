package me.hwanghj.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

public class CookieUtil {
    // 요청값(이름, 값, 만료 기간)을 바탕으로 쿠키 추가.
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    // 쿠키명을 입력받아 쿠키 삭제.
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();

        // 쿠키가 없는경우 메서드 종료.
        if (cookies == null) {
            return;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    // 객체를 직렬화해 쿠키의 값으로 변환.
    public static String serialize(Object obj) {
        return Base64
                .getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    // 쿠키를 역직렬화해 객체로 변환.
    // 코드는 작동하지만 자바 직렬화 대신 JSON 방식을 쓰는것이 표준임.
    // SerializationUtils 대신 직접 ObjectInputStream을 제어하거나 Jackson라이브러리를 사용해 JSON으로 변환하는 것을 권장.
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.
                cast(SerializationUtils.deserialize(
                        Base64
                                .getUrlDecoder()
                                .decode(cookie.getValue())
                )
        );
    }
}
