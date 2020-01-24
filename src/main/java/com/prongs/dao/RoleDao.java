package com.prongs.dao;

import com.prongs.domain.Role;

import java.util.List;

/**
 * @author Prongs
 * @date 2020/1/24 15:25
 */
public interface RoleDao {
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
