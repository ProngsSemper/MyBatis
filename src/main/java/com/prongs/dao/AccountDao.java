package com.prongs.dao;

import com.prongs.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Prongs
 * @date 2020/1/24 14:24
 */
public interface AccountDao {
    /**
     * 查询所有
     * @return 查询账户及其用户
     */
    @Select("select * from account")
    @Results(id = "accountMap" , value = {
            @Result(id = true,column = "id" , property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(property = "user",column = "uid",one = @One(select = "com.prongs.dao.UserDao.findUserById",fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    /**
     *
     * @param userId
     * @return
     */
    @Select("select * from account where uid = #{userId}")
    List<Account> findAccountByUid(Integer userId);

}
