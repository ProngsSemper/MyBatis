package com.prongs.dao;

import com.prongs.domain.Account;
import com.prongs.domain.AccountUser;

import java.util.List;

/**
 * @author Prongs
 * @date 2020/1/24 14:24
 */
public interface AccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();

    /**
     * 返回账号及用户名地址
     * @return
     */
    List<AccountUser> findAllAccount();
}
