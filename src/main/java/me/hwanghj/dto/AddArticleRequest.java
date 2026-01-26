package me.hwanghj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hwanghj.domain.Article;

@NoArgsConstructor          // 기본 생성자.
@AllArgsConstructor         // 모든 필드 값을 파라미터로 받는 생성자.
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    // 생성자를 사용해 객체 생성.
    public Article toEntity(String author) {
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
