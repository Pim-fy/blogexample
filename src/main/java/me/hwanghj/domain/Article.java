package me.hwanghj.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)      // 엔터티의 생성, 수정시간을 자동으로 감시, 기록.
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "author", nullable = false)
    private String author;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    // mappedBy : 하위 엔터티가 상위 엔터티를 참조할 때 사용.
    // cascade : 상위 엔터티 변경 시 하위 엔터티에 전파.
    // REMOVE : 상위 엔터티 삭제 시 하위 엔터티 전체 삭제.
    private List<Comment> comments;

    // 빌더 패턴으로 객체 생성
    @Builder
    public Article(String author,String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    // 글 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
