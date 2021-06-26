package cn.edu.nju.web.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "replies")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment commentReplied;

    @ManyToMany(mappedBy = "likedReplies")
    private Collection<User> likedBy = new ArrayList<>();

    public Reply() {}

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

    public Collection<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Collection<User> likedBy) {
        this.likedBy = likedBy;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Comment getCommentReplied() {
        return commentReplied;
    }

    public void setCommentReplied(Comment commentBeReplied) {
        this.commentReplied = commentBeReplied;
    }
}
