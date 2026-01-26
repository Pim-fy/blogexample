package me.hwanghj.repository;

import me.hwanghj.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
    // 상속받을 때 엔터티 Article과 엔터티의 PK타입 Long을 인수로.
}
