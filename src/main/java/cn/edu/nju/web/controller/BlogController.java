package cn.edu.nju.web.controller;

import cn.edu.nju.web.common.ServiceStatusCode;
import cn.edu.nju.web.common.ResultMessage;
import cn.edu.nju.web.domain.Blog;
import cn.edu.nju.web.domain.User;
import cn.edu.nju.web.repo.BlogRepository;
import cn.edu.nju.web.repo.UserRepository;
import cn.edu.nju.web.vo.BlogVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BlogController {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogController(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/blogs")
    public ResultMessage<BlogVO> create(@RequestParam String title, @RequestParam String content, HttpServletRequest req) throws UnsupportedEncodingException {
        content= URLDecoder.decode(content,"utf-8");
        HttpSession session = req.getSession();
        User author = (User) session.getAttribute(SessionKeys.USER);
        if (author == null) {
            return new ResultMessage<>(ServiceStatusCode.NOT_SIGNED_IN, null);
        }
        if (blogRepository.findByAuthorAndTitle(author, title).isEmpty()) {
            Blog blog = new Blog();
            blog.setAuthor(author);
            blog.setTitle(title);
            blog.setContent(content);
            blog.setCreateDate(Calendar.getInstance().getTime());
            return new ResultMessage<>(ServiceStatusCode.SUCCESS, new BlogVO(blogRepository.save(blog)));
        } else return new ResultMessage<>(ServiceStatusCode.BLOG_EXIST, null);
    }

    @PutMapping("/blogs")
    public ResultMessage<BlogVO> update(@RequestParam(name = "blog_id") long blogId,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) String content) {
        if (content == null && title == null) {
            return new ResultMessage<>(ServiceStatusCode.SUCCESS, null);
        }
        Optional<Blog> blog_here = blogRepository.findById(blogId);
        if (!blog_here.isPresent()) {
            return new ResultMessage<>(ServiceStatusCode.BLOG_NOT_EXIST, null);
        }
        Blog blog = blog_here.get();
        if (content != null) {
            blog.setContent(content);
        }
        if (title != null) {
            blog.setTitle(title);
        }
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, new BlogVO(blogRepository.save(blog)));
    }

//    @GetMapping("/blogs")
//    public ResultMessage findByAuthor(@RequestParam long authorId) {
//        if (!userRepository.findById(authorId).isPresent()) {
//            return new ResultMessage(ServiceStatusCode.USER_NOT_EXISTS, null);
//        }
//        User user = userRepository.findById(authorId).get();
//        return new ResultMessage(ServiceStatusCode.SUCCESS, blogRepository.findByAuthor(user));
//    }

    @GetMapping("/blogs/search")
    public ResultMessage<List<BlogVO>> findByTitle(@RequestParam(name = "keyword") String keyword) {
        List<BlogVO> ans;
        if(keyword.equals("undefined")){
            ans = blogRepository.findAll()
                    .stream().map(BlogVO::new).collect(Collectors.toList());
        }else ans = blogRepository.findByTitleLike("%" + keyword + "%")
                .stream().map(BlogVO::new).collect(Collectors.toList());
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, ans);
    }

    @DeleteMapping("/blogs")
    public ResultMessage<BlogVO> delete(@RequestParam(name = "blog_id") long blogId) {
        blogRepository.deleteById(blogId);
        return new ResultMessage<>(ServiceStatusCode.SUCCESS,null);
    }

    @PostMapping("/blogs/like")
    public ResultMessage<BlogVO> like(@RequestParam(name = "blog_id") long blogId, HttpServletRequest req) {
        HttpSession sess = req.getSession();
        User userFromSession = (User)sess.getAttribute(SessionKeys.USER);
        if (userFromSession == null) {
            return new ResultMessage<>(ServiceStatusCode.NOT_SIGNED_IN, null);
        }
        Optional<User> userOpt = userRepository.findById(userFromSession.getId());
        if (!userOpt.isPresent()) {
            return new ResultMessage<>(ServiceStatusCode.USER_NOT_EXISTS, null);
        }
        Optional<Blog> blogOpt = blogRepository.findById(blogId);
        if (!blogOpt.isPresent()) {
            return new ResultMessage<>(ServiceStatusCode.BLOG_NOT_EXIST, null);
        }
        User u = userOpt.get();
        Blog b = blogOpt.get();
        u.getLikedBlogs().add(b);
        userRepository.save(u);
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, null);
    }
}
