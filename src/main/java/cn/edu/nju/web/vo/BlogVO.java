package cn.edu.nju.web.vo;

import cn.edu.nju.web.domain.Blog;
import cn.edu.nju.web.domain.Comment;
import cn.edu.nju.web.domain.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BlogVO {
    private Long id;

    private String title;
    private String content;
    private Date createDate;

    private Long authorId;
    private String authorName;

    private List<Long> likedBy;

    private List<Long> comments;

    public BlogVO(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.createDate = blog.getCreateDate();
        this.authorId = blog.getAuthor().getId();
        this.authorName=blog.getAuthor().getName();
        this.likedBy = blog.getLikedBy().stream().map(User::getId).collect(Collectors.toList());
        this.comments = blog.getComments().stream().map(Comment::getId).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<Long> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<Long> likedBy) {
        this.likedBy = likedBy;
    }

    public List<Long> getComments() {
        return comments;
    }

    public void setComments(List<Long> comments) {
        this.comments = comments;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
