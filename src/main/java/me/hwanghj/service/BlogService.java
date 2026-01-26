package me.hwanghj.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.hwanghj.domain.Article;
import me.hwanghj.dto.AddArticleRequest;
import me.hwanghj.dto.UpdateArticleRequest;
import me.hwanghj.repository.BlogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor            // final, Notnull이 붙은 필드의 생성자 추가.
@Service                            // 빈 등록.
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가.
    public Article save(AddArticleRequest request, String userName) {
        return blogRepository.save(request.toEntity(userName));
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 단일 글 조회.
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    // 블로그 글 삭제.
    public void delete(long id) {
        Article article = blogRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }


//    public void delete(long id) {
//        blogRepository.deleteById(id);
//    }

    // 블로그 글 수정
    @Transactional      // 트랜젝션 메서드.
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    // 인증 객체에 담겨 있는 사용자의 정보와 글을 작성한 사용자의 정보를 비교.
    // 서로 다르면 예외 발생시켜 작업을 수행하지 않음.
    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
