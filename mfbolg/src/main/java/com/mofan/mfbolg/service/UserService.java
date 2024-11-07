package com.mofan.mfbolg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mofan.mfbolg.entity.Users;

public interface UserService extends IService<Users> {


     Users findByUsername(Users users);
}
