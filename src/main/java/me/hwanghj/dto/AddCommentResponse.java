package me.hwanghj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hwanghj.domain.Article;
import me.hwanghj.domain.Comment;

@NoArgsConstructor          // 기본 생성자 자동 생성.
@AllArgsConstructor         // 모든 필드 값을 파라미터로 받는 생성자 자동 생성.
@Getter
public class AddCommentResponse {
    private Long id;
    private String content;

    public AddCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
