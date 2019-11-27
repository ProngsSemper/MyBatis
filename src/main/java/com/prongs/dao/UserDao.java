package com.prongs.dao;

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
}
