package com.zp.service.impl;

import com.zp.dao.TbUserMapper;
import com.zp.entity.TbUser;
import com.zp.entity.TbUserExample;
import com.zp.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import java.util.List;

@Service
public class TbUserServiceImpl extends HttpServlet implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Override
    public TbUser login(String username, String password, Integer remember) {
        //验证用户名密码
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.createCriteria()
                .andUsernameEqualTo(username)
                .andPasswordEqualTo(password);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);

        //判断是否可以登录
        if (tbUsers.size()==1){
            return tbUsers.get(0);
        }
        return null;
    }
}
