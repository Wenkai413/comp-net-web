package cn.edu.nju.web.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
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
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blogCommented;

    @ManyToMany(mappedBy = "likedComments")
    private Collection<User> likedBy = new ArrayList<>();

    @OneToMany(mappedBy = "commentReplied", cascade = CascadeType.ALL)
    private Collection<Reply> replies = new ArrayList<>();

    public Comment() {}

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Blog getBlogCommented() {
        return blogCommented;
    }

    public void setBlogCommented(Blog blogBeCommented) {
        this.blogCommented = blogBeCommented;
    }

    public Collection<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Collection<User> likedBy) {
        this.likedBy = likedBy;
    }

    public Collection<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Reply> replies) {
        this.replies = replies;
    }
}
