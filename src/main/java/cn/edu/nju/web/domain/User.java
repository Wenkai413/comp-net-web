package cn.edu.nju.web.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String introduction;
    @Column(columnDefinition = "DATE")
    private Date birthday;
    private Gender gender;
    @Column(name = "join_date")
    private Date joinDate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Collection<Blog> blogs = new ArrayList<>();
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Collection<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Collection<Reply> replies = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_like_blog",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "blog_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Collection<Blog> likedBlogs = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "user_like_comment",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "comment_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Collection<Comment> likedComments = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "user_like_reply",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "reply_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Collection<Reply> likedReplies = new ArrayList<>();

    public User() {}

    public User(String email, String password, String name) {
        this(name, email, password, null, null, null);
    }

    public User(String name, String email, String password, String introduction, Date birthday, Gender gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.introduction = introduction;
        this.birthday = birthday;
        this.gender = gender;
        this.joinDate = Calendar.getInstance().getTime();
        this.blogs = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.replies = new ArrayList<>();
        this.likedBlogs = new ArrayList<>();
        this.likedComments = new ArrayList<>();
        this.likedReplies = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Collection<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Collection<Blog> blogs) {
        this.blogs = blogs;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Reply> replies) {
        this.replies = replies;
    }

    public Collection<Blog> getLikedBlogs() {
        return likedBlogs;
    }

    public void dislikeBlog(int blogId) {

    }

    public void setLikedBlogs(Collection<Blog> likedBlogs) {
        this.likedBlogs = likedBlogs;
    }

    public Collection<Comment> getLikedComments() {
        return likedComments;
    }

    public void setLikedComments(Collection<Comment> likedComments) {
        this.likedComments = likedComments;
    }

    public Collection<Reply> getLikedReplies() {
        return likedReplies;
    }

    public void setLikedReplies(Collection<Reply> likedReplies) {
        this.likedReplies = likedReplies;
    }
}
