package com.mofan.mfbolg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mofan.mfbolg.entity.Users;
import com.mofan.mfbolg.mapper.UsersMapper;
import com.mofan.mfbolg.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: mfbolg
 * @description:
 * @author: mofan
 * @create: 2024-10-30 22:11
 **/
@Service
@Transactional
public class userServiceImpl extends ServiceImpl<UsersMapper, Users> implements UserService {

    @Override
    public Users findByUsername(Users users) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", users.getUsername());
        queryWrapper.eq("password", users.getPassword());

        Users admin = baseMapper.selectOne(queryWrapper);

        return admin;
    }
}
