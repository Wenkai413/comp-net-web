package cn.edu.nju.web.controller;

import cn.edu.nju.web.common.ServiceStatusCode;
import cn.edu.nju.web.common.ResultMessage;
import cn.edu.nju.web.domain.Comment;
import cn.edu.nju.web.domain.User;
import cn.edu.nju.web.repo.BlogRepository;
import cn.edu.nju.web.repo.CommentRepository;
import cn.edu.nju.web.vo.CommentVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentController(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    @PostMapping("/comments")
    public ResultMessage<CommentVO> AddComment(@RequestParam String content, @RequestParam(name = "blog_id") long blogId, HttpServletRequest req) {
        HttpSession session = req.getSession();
        User author = (User) session.getAttribute(SessionKeys.USER);
        if (author == null) {
            return new ResultMessage<>(ServiceStatusCode.NOT_SIGNED_IN, null);
        }
        if (!blogRepository.findById(blogId).isPresent()) {
            return new ResultMessage<>(ServiceStatusCode.BLOG_NOT_EXIST, null);
        }
        Comment com = new Comment();
        com.setAuthor(author);
        com.setContent(content);
        com.setBlogCommented(blogRepository.findById(blogId).get());
        com.setCreateDate(Calendar.getInstance().getTime());
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, new CommentVO(commentRepository.save(com)));
    }

    @GetMapping("/comments/find")
    public ResultMessage<CommentVO> findComment( @RequestParam(name = "com_id") long comId) {
        Comment com = commentRepository.getOne(comId);
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, new CommentVO(com));
    }
}
