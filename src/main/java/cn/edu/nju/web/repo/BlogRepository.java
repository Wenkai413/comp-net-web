package cn.edu.nju.web.repo;


import cn.edu.nju.web.domain.Blog;
import cn.edu.nju.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByAuthorAndTitle(User author, String title);
    List<Blog> findByAuthor(User author);
    List<Blog> findByTitleLike(String keyword);
}
