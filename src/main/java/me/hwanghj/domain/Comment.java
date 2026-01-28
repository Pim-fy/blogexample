package me.hwanghj.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    // 테이블간의 N:1 관계를 나타내기 위해 사용.
    // Comment가 하나의 Article을 가지도록 설정한 것.
    // 반대로 블로그 글에서는 OneToMany 어노테이션.
    private Article article;

    // 빌더 패턴으로 객체 생성.
    @Builder
    public Comment(Article article, String author, String content) {
        this.article = article;
        this.author = author;
        this.content = content;
    }
}
