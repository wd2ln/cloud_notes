package com.zp.service.impl;

import com.zp.dao.TbNoteMapper;
import com.zp.dao.TbNoteTypeMapper;
import com.zp.entity.TbNote;
import com.zp.entity.TbNoteType;
import com.zp.entity.TbNoteTypeExample;
import com.zp.entity.TbUser;
import com.zp.service.TbNoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbNoteTypeServiceImpl implements TbNoteTypeService {
    @Autowired
    private TbNoteMapper tbNoteMapper;
    @Autowired
    private TbNoteTypeMapper tbNoteTypeMapper;
    @Override
    public Map<String,Object> searchById(Integer id) {
        TbNoteServiceImpl tbNoteService = new TbNoteServiceImpl();
        //tbNoteService.getDates()
        return null;
    }

    @Override
    public Integer add(String typeName,HttpServletRequest request) {
        //获取用户id
        HttpSession session = request.getSession(false);
        TbUser user =(TbUser) session.getAttribute("user");
        //设置参数
        TbNoteType tbNoteType = new TbNoteType();
        tbNoteType.setTypeName(typeName);
        tbNoteType.setUserId(user.getId());
        int i = tbNoteTypeMapper.insertSelective(tbNoteType);

        if (i>0){
            return tbNoteType.getId();
        }
        return -1;
    }

    @Override
    public Boolean del(Integer id) {
        //查询是否存在日记
        if (tbNoteMapper.selectbyTypeId(id).size()<1){
            if(tbNoteTypeMapper.deleteByPrimaryKey(id)>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updates(String typeName,Integer id) {
        //设置参数
        TbNoteType tbNoteType = new TbNoteType();
        tbNoteType.setTypeName(typeName);
        TbNoteTypeExample tbNoteTypeExample = new TbNoteTypeExample();
        tbNoteTypeExample.createCriteria()
                .andIdEqualTo(id);
        //修改日记类型
        int i = tbNoteTypeMapper.updateByExampleSelective(tbNoteType, tbNoteTypeExample);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> selectType(Integer id) {
        //List<TbNote> tbNotes = tbNoteTypeMapper.selectByTypeAll(id);
       TbNoteTypeExample tbNoteTypeExample = new TbNoteTypeExample();
        tbNoteTypeExample.createCriteria()
                .andUserIdEqualTo(id);
        List<TbNoteType> tbNoteTypes = tbNoteTypeMapper.selectByExample(tbNoteTypeExample);
        HashMap<String, Object> map = new HashMap<>();
        //map.put("list",tbNotes);
        map.put("list",tbNoteTypes);
        return map;
    }
}
