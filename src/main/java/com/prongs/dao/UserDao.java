package com.prongs.dao;

import com.prongs.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Prongs
 * @date 2019/11/26 22:42
 */
@CacheNamespace(blocking = true)
public interface UserDao {
    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap" , value = {
            @Result(id = true,column = "id" , property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(property = "accounts",column = "id",many = @Many(select = "com.prongs.dao.AccountDao.findAccountByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 新增用户
     * @param user 用户
     */
    @Insert("insert into user(username,birthday,sex,address) values (#{username},#{birthday},#{sex},#{address}) ")
    void saveUser(User user);

    /**
     * 更新用户信息
     * @param user 用户
     */
    @Update("update user set username=#{username},sex=#{sex},address=#{address} where id=#{id}")
    void updateUser(User user);


    /**
     * 根据用户id查找用户
     * @param id 用户id
     * @return 返回该用户对象
     */
    @Select("select * from user where id = #{id}")
    @ResultMap("userMap")
    User findUserById(Integer id);


}
