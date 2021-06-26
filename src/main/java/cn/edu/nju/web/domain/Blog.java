package cn.edu.nju.web.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 领域对象（想要在数据库里存的）
 */
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "text")
    private String content;
    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToMany(mappedBy = "likedBlogs")
    private Collection<User> likedBy = new ArrayList<>();

    @OneToMany(mappedBy = "blogCommented", cascade = CascadeType.ALL)
    private Collection<Comment> comments = new ArrayList<>();

    public Blog() {

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Collection<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Collection<User> likedBy) {
        this.likedBy = likedBy;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }
}
