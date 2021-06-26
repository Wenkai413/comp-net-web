package cn.edu.nju.web.vo;

import cn.edu.nju.web.domain.Blog;
import cn.edu.nju.web.domain.Comment;
import cn.edu.nju.web.domain.Reply;
import cn.edu.nju.web.domain.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CommentVO {
    private Long id;

    private String content;
    private Date createDate;

    private Long authorId;
    private String authorName;

    private Long blogCommentedId;

    private List<Long> likedBy;

    private List<Long> replies;

    public CommentVO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createDate = comment.getCreateDate();
        this.authorId = comment.getAuthor().getId();
        this.authorName=comment.getAuthor().getName();
        this.blogCommentedId = comment.getBlogCommented().getId();
        this.likedBy = comment.getLikedBy().stream().map(User::getId).collect(Collectors.toList());
        this.replies = comment.getReplies().stream().map(Reply::getId).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getBlogCommentedId() {
        return blogCommentedId;
    }

    public void setBlogCommentedId(Long blogCommentedId) {
        this.blogCommentedId = blogCommentedId;
    }

    public List<Long> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<Long> likedBy) {
        this.likedBy = likedBy;
    }

    public List<Long> getReplies() {
        return replies;
    }

    public void setReplies(List<Long> replies) {
        this.replies = replies;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
