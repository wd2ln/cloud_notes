package com.zp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zp.dao.TbNoteMapper;
import com.zp.dao.TbNoteTypeMapper;
import com.zp.entity.TbDateAndCount;
import com.zp.entity.TbNote;
import com.zp.entity.TbNoteExample;
import com.zp.entity.TbNoteTypeExample;
import com.zp.service.TbNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbNoteServiceImpl implements TbNoteService {
    @Autowired
    private TbNoteMapper tbNoteMapper;
    @Autowired
    private TbNoteTypeMapper tbNoteTypeMapper;
    @Override
    public Map<String,Object> getDates(TbNote tbNote, Integer pageNum, Integer pageSize) {
        //设置查询所需的参数
        TbNoteExample tbNoteExample = new TbNoteExample();
        TbNoteExample.Criteria criteria = tbNoteExample.createCriteria();
        criteria.andTypeIdEqualTo((tbNote.getTypeId()));
        //设置分页
        PageHelper.startPage(pageNum,pageSize);
        //获取查询结果
        List<TbNote> tbNotes = tbNoteMapper.selectByExample(tbNoteExample);
        Map<String, Object> map =null;
        //有数据
        if (tbNotes!=null){
            //将分页结果放入
            PageInfo<TbNote> tbNotePageInfo = new PageInfo<>(tbNotes);
            System.out.println(tbNotePageInfo);
            map=new HashMap<String, Object>();
            map.put("pageinfo",tbNotePageInfo);
            map.put("total",tbNotePageInfo.getTotal());
        }
        return map;
    }

    @Override
    public Map<String, Object> getDateInfo(TbNote tbNote) {
        //设置查询所需的参数
        Integer typeId = tbNote.getTypeId();
        List<TbDateAndCount> tbDateAndCounts = tbNoteMapper.selectByDateAndCount(typeId);
        //处理返回日期格式
        for (TbDateAndCount tbDateAndCount : tbDateAndCounts) {
            String groupName = tbDateAndCount.getGroupName();
            String[] s = groupName.split(" ");
            String s1 = s[0]+"年";
            String s2 = s[1]+"月";
            tbDateAndCount.setGroupName(s1+s2);
        }
        Map<String,Object> map;
        map=new HashMap<String, Object>();
        map.put("dateinfo",tbDateAndCounts);
        return map;
    }

    @Override
    public Map<String, Object> getDateType(TbNote tbNote) {
        //设置查询所需的参数
        Integer user_id = tbNote.getTypeId();
        System.out.println(user_id+"=================");
        List<TbDateAndCount> tbDateAndCounts=null;
        tbDateAndCounts = tbNoteTypeMapper.selectByType(user_id);
        System.out.println("异常");
        System.out.println(tbDateAndCounts);
        Map<String,Object> map;
        map=new HashMap<String, Object>();
        map.put("typeinfo",tbDateAndCounts);
        return map;
    }
}
