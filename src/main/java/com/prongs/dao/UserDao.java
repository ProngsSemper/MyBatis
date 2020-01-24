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
     * 根据用户id查找用户
     * @param id 用户id
     * @return 返回该用户对象
     */
    User findUserById(Integer id);

}
