package cn.edu.nju.web.controller;

import cn.edu.nju.web.common.ServiceStatusCode;
import cn.edu.nju.web.common.ResultMessage;
import cn.edu.nju.web.domain.Comment;
import cn.edu.nju.web.domain.Reply;
import cn.edu.nju.web.domain.User;
import cn.edu.nju.web.repo.CommentRepository;
import cn.edu.nju.web.repo.ReplyRepository;
import cn.edu.nju.web.vo.CommentVO;
import cn.edu.nju.web.vo.ReplyVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

@RestController
public class ReplyController {
    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;

    public ReplyController(ReplyRepository replyRepository,CommentRepository commentRepository) {
        this.replyRepository = replyRepository;
        this.commentRepository = commentRepository;
    }
    @PostMapping("/replies")
    public ResultMessage<ReplyVO> addReply(@RequestParam String content, @RequestParam(name = "comment_id") long commentId, HttpServletRequest req){
        HttpSession session = req.getSession();
        User author = (User) session.getAttribute(SessionKeys.USER);
        if (author == null) {
            return new ResultMessage<>(ServiceStatusCode.NOT_SIGNED_IN, null);
        }
        if(!commentRepository.findById(commentId).isPresent()){
            return new ResultMessage<>(ServiceStatusCode.COMMENT_NOT_EXIT,null);
        }
        Reply rep = new Reply();
        rep.setAuthor(author);
        rep.setCommentReplied(commentRepository.findById(commentId).get());
        rep.setContent(content);
        rep.setCreateDate(Calendar.getInstance().getTime());
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, new ReplyVO(replyRepository.save(rep)));
    }

    @GetMapping("/replies/find")
    public ResultMessage<ReplyVO> findReply(@RequestParam(name = "com_id") long comId) {
        Reply com = replyRepository.getOne(comId);
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, new ReplyVO(com));
    }
}
