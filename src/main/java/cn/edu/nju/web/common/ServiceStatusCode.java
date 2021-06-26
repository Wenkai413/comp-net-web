package cn.edu.nju.web.common;

public enum ServiceStatusCode {

    SUCCESS(0, "成功"),
    EMAIL_EXISTS(1, "邮箱已存在"),
    USER_NOT_EXISTS(2,"用户不存在"),
    WRONG_PASSWORD(3,"密码错误"),
    BLOG_EXIST(4,"博客已存在"),
    NOT_SIGNED_IN(5, "未登录"),
    BLOG_NOT_EXIST(6,"博客不存在"),
    COMMENT_NOT_EXIT(7,"评论不存在"),
    UNKNOWN(8, "未知错误");

    ServiceStatusCode(int id, String displayText) {
        this.id = id;
        this.displayText = displayText;
    }

    private int id;
    private String displayText;
}
