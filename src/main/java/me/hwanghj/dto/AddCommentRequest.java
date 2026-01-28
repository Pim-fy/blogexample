package me.hwanghj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hwanghj.domain.Article;
import me.hwanghj.domain.Comment;

@NoArgsConstructor          // 기본 생성자 자동 생성.
@AllArgsConstructor         // 모든 필드 값을 파라미터로 받는 생성자 자동 생성.
@Getter
public class AddCommentRequest {
    private Long articleId;
    private String content;

    // 생성자 이용해 객체 생성.
    public Comment toEntity(String author, Article article) {
        return Comment.builder()
                .article(article)
                .content(content)
                .author(author)
                .build();
    }
}
