//UserRepository是数据层
//所有与数据库的交互都需要通过UserRepository来完成
package cn.edu.nju.web.repo;

import cn.edu.nju.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //这是一个接口，但是我不知道它的构造函数，所以没办法弄出一个它的对象
    //基本的增删改查是由Spring Data JPA框架自动实现的

    // 通过符合命名规则，可以让框架自动实现除基本的增删改查之外的逻辑(这里findByName 中findBy就是命名规则的一种）
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
