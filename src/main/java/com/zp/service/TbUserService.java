package com.zp.service;

import com.zp.entity.TbUser;

import java.util.Map;

public interface TbUserService {
    TbUser login(String username, String password, Integer remember);
    Boolean imgUp(String filename,Integer id,String mood,String nick);
    Boolean updates(String mood,String nick);

    String selectNick(String nick);
}
