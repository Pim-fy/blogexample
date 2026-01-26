package me.hwanghj.controller;

import lombok.RequiredArgsConstructor;
import me.hwanghj.domain.Article;
import me.hwanghj.dto.ArticleListViewResponse;
import me.hwanghj.dto.ArticleViewResponse;
import me.hwanghj.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();

        // 블로그 글 리스트 저장.
        model.addAttribute("articles", articles);

        // articleList.html 이라는 뷰 조회.
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        // 인자 id에 URL로 넘어온 값을 받아 findById() 메서드로 넘겨 글을 조회.
        Article article = blogService.findById(id);
        // 화면에서 사용할 모델에 데이터를 저장.
        model.addAttribute("article", new ArticleViewResponse(article));

        // 보여줄 화면의 템플릿 이름을 반환. article.html 이라는 뷰.
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            // id가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            // id가 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }


}
