package com.maker.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maker.entity.Perms;
import com.maker.entity.Role;
import com.maker.entity.RolePerms;
import com.maker.entity.User;
import com.maker.mapper.PermsMapper;
import com.maker.mapper.RoleMapper;
import com.maker.mapper.RolePermsMapper;
import com.maker.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional//简而言之，@Transactional注解在代码执行出错的时候能够进行事务的回滚
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermsMapper rolePermsMapper;

    @Autowired
    private PermsMapper permsMapper;


    //根据用户名查询用户
    @Override
    public User findByUserName(String userName) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username",userName));
    }

    //根据角色id查询角色
    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_id",roleId));
        return role.getRoleName();
    }

    @Override
    public List<Perms> findPermsByRoleId(Integer roleId) {
        List<Perms> perms = new ArrayList<>();
        //通过roleId查询对应permsId
        List<RolePerms> rolePermsList = rolePermsMapper.selectList(new QueryWrapper<RolePerms>().eq("role_id", roleId));
        rolePermsList.forEach(rolePerms -> {
            //通过permsId查询所有角色
            Perms perms1 = permsMapper.selectOne(new QueryWrapper<Perms>().eq("permission_id",rolePerms.getPermissionId()));
            perms.add(perms1);
        });

        return perms;
    }

    //根据id查询用户
    @Override
    public User getUserById(String id) {
        User user = null;
        try {
            user = userMapper.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return user;
        }
        return user;
    }

}
