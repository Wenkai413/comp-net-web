package cn.edu.nju.web.vo;

import cn.edu.nju.web.domain.Comment;
import cn.edu.nju.web.domain.Reply;
import cn.edu.nju.web.domain.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReplyVO {
    private Long id;

    private String content;

    private Date createDate;

    private Long authorId;

    private String authorName;

    private Long commentRepliedId;

    private List<Long> likedBy;

    public ReplyVO(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.createDate = reply.getCreateDate();
        this.authorId = reply.getAuthor().getId();
        this.authorName=reply.getAuthor().getName();
        this.commentRepliedId = reply.getCommentReplied().getId();
        this.likedBy = reply.getLikedBy().stream().map(User::getId).collect(Collectors.toList());
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

    public Long getCommentRepliedId() {
        return commentRepliedId;
    }

    public void setCommentRepliedId(Long commentRepliedId) {
        this.commentRepliedId = commentRepliedId;
    }

    public List<Long> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<Long> likedBy) {
        this.likedBy = likedBy;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
