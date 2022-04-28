package com.zp.service;

import com.zp.entity.TbUser;

public interface TbUserService {
    TbUser login(String username, String password, Integer remember);
}
