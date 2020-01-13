package com.prongs.dao;

import com.prongs.domain.QueryVo;
import com.prongs.domain.User;

import java.util.List;

/**
 * @author Prongs
 * @date 2019/11/26 22:42
 */
public interface UserDao {
    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user user对象
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user user对象
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param id user的id
     */
    void deleteUser(Integer id);

    /**
     * 根据用户id查找用户
     * @param id 用户id
     * @return 返回该用户对象
     */
    User findUserById(Integer id);

    /**
     * 根据用户名称查找用户
     * @param name 用户名称
     * @return 返回用户对象
     */
    List<User> findUserByName(String name);

    /**
     * 查询总用户数
     * @return 总用户数
     */
    int total();

    /**
     * 根据QueryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);
}
