package cn.edu.nju.web.vo;

import cn.edu.nju.web.domain.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserVO {
    private Long id;

    private String name;
    private String email;
    private String password;
    private String introduction;
    private Date birthday;
    private Gender gender;
    private Date joinDate;

    private List<Long> blogs;
    private List<Long> comments;
    private List<Long> replies;

    private List<Long> likedBlogs;
    private List<Long> likedComments;
    private List<Long> likedReplies;

    public UserVO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.introduction = user.getIntroduction();
        this.birthday = user.getBirthday();
        this.gender = user.getGender();
        this.joinDate = user.getJoinDate();

        this.blogs = user.getBlogs().stream().map(Blog::getId).collect(Collectors.toList());
        this.comments = user.getComments().stream().map(Comment::getId).collect(Collectors.toList());
        this.replies = user.getReplies().stream().map(Reply::getId).collect(Collectors.toList());

        this.likedBlogs = user.getLikedBlogs().stream().map(Blog::getId).collect(Collectors.toList());
        this.likedComments = user.getLikedComments().stream().map(Comment::getId).collect(Collectors.toList());
        this.likedReplies = user.getLikedReplies().stream().map(Reply::getId).collect(Collectors.toList());
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

    public List<Long> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Long> blogs) {
        this.blogs = blogs;
    }

    public List<Long> getComments() {
        return comments;
    }

    public void setComments(List<Long> comments) {
        this.comments = comments;
    }

    public List<Long> getReplies() {
        return replies;
    }

    public void setReplies(List<Long> replies) {
        this.replies = replies;
    }

    public List<Long> getLikedBlogs() {
        return likedBlogs;
    }

    public void setLikedBlogs(List<Long> likedBlogs) {
        this.likedBlogs = likedBlogs;
    }

    public List<Long> getLikedComments() {
        return likedComments;
    }

    public void setLikedComments(List<Long> likedComments) {
        this.likedComments = likedComments;
    }

    public List<Long> getLikedReplies() {
        return likedReplies;
    }

    public void setLikedReplies(List<Long> likedReplies) {
        this.likedReplies = likedReplies;
    }
}
