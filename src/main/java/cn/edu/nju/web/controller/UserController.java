//UserController是业务逻辑层 （展示层一般是前端

package cn.edu.nju.web.controller;

import cn.edu.nju.web.common.ServiceStatusCode;
import cn.edu.nju.web.common.ResultMessage;
import cn.edu.nju.web.domain.User;
import cn.edu.nju.web.repo.UserRepository;
import cn.edu.nju.web.controller.dto.SignUpInfo;
import cn.edu.nju.web.vo.UserVO;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
// 用来处理前端的请求
// 具体调用哪一个方法是由框架确定的

@RestController
public class UserController {
    //想要调用signUp方法需要通过对象来调用（因为这里没有static），UserController类型的对象由框架来创建，所以我调不到它的构造方法

    //这种方式叫依赖注入，dependency inject
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // POST /users HTTP/1.1  （http方法名 URI http版本
    // 首部 (header) ...
    // 数据
    @PostMapping(value = "/users")
    public ResultMessage<UserVO> signUp(@RequestBody SignUpInfo info) {
        // 写上处理的业务逻辑
        if (userRepository.existsByEmail(info.getEmail())) {
            return new ResultMessage<>(ServiceStatusCode.EMAIL_EXISTS, null);
        }
        User newUser = new User(info.getName(), info.getEmail(), info.getPassword(),
                info.getIntroduction(), info.getBirthday(), info.getGender());
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, new UserVO(userRepository.save(newUser)));
    }

    @GetMapping("/sign-in")
    public ResultMessage<UserVO> signIn(@RequestParam String email, @RequestParam String password, HttpServletRequest req) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (!userOpt.isPresent()) {
            return new ResultMessage<>(ServiceStatusCode.USER_NOT_EXISTS, null);
        }
        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            return new ResultMessage<>(ServiceStatusCode.WRONG_PASSWORD, null);
        }
        HttpSession sess = req.getSession();
        sess.setAttribute(SessionKeys.USER, user);
        UserVO res = new UserVO(user);
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, res);
    }

    @PutMapping("/users")
    public ResultMessage<UserVO> update(@RequestBody SignUpInfo info, HttpServletRequest req) {
        HttpSession sess = req.getSession();
        User u = (User)sess.getAttribute(SessionKeys.USER);
        if (u == null) {
            return new ResultMessage<>(ServiceStatusCode.NOT_SIGNED_IN, null);
        }
        Optional<User> userOpt = userRepository.findById(u.getId());
        if (!userOpt.isPresent()) {
            return new ResultMessage<>(ServiceStatusCode.UNKNOWN, null);
        }
        User u2 = userOpt.get();
        if (info.getName() != null && !info.getName().equals("")) {
            u2.setName(info.getName());
        }
        if (info.getBirthday() != null) {
            u2.setBirthday(info.getBirthday());
        }
        if (info.getGender() != null) {
            u2.setGender(info.getGender());
        }
        if (info.getPassword() != null) {
            u2.setPassword(info.getPassword());
        }
        if (info.getIntroduction() == null || info.getIntroduction().trim().equals("")) {
            u2.setIntroduction("");
        } else {
            u2.setIntroduction(info.getIntroduction());
        }
        return new ResultMessage<>(ServiceStatusCode.SUCCESS, new UserVO(userRepository.save(u2)));
    }

    @GetMapping("/users")
    public ResultMessage<UserVO> findByEmail(@RequestParam String email){
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.map(user -> new ResultMessage<>(ServiceStatusCode.SUCCESS, new UserVO(user)))
                .orElseGet(() -> new ResultMessage<>(ServiceStatusCode.USER_NOT_EXISTS, null));
    }
}
