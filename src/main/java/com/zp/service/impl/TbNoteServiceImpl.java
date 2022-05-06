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
        Map<String,Object> map=null;
        if(tbNote.getDate()!=null){
            System.out.println("找到了");
            //设置分页
            PageHelper.startPage(pageNum,pageSize);
            String date = tbNote.getDate();
            String[] years = date.split("年");
            String mm = years[1].substring(0, 2);
            map = getdateAll(years[0]+" "+mm);
            List<TbNote> sell = (List<TbNote>)map.get("sell");
            System.out.println(sell.get(0).getPubTime());
            System.out.println(sell.get(0).getDate());
            //将分页结果放入
            PageInfo<TbNote> tPageInfo = new PageInfo<>(sell);
//            map=new HashMap<String, Object>();
            map.put("pageinfo",tPageInfo);
            map.put("total",tPageInfo.getTotal());
            return map;
        }


        //设置查询所需的参数
        TbNoteExample tbNoteExample = new TbNoteExample();
        TbNoteExample.Criteria criteria = tbNoteExample.createCriteria();
        //判断是否传入typeid
        if (tbNote.getTypeId()==null){
            System.out.println("找到id");
            criteria.andIdEqualTo((tbNote.getId()));
        }if (tbNote.getTypeId()!=null){
            System.out.println("找到typeid");
            criteria.andTypeIdEqualTo(tbNote.getTypeId());
        }if (tbNote.getTitle()!=null){
            String ss="%"+tbNote.getTitle()+"%";
            System.out.println("找到title");
            //设置分页
            PageHelper.startPage(pageNum,pageSize);
            //获取查询结果
            List<TbNote> tbNotes = tbNoteMapper.selectByExample1(ss);
            //Map<String, Object> map =null;
            //有数据
            if (tbNotes!=null){
                //将分页结果放入
                PageInfo<TbNote> tbNotePageInfo = new PageInfo<>(tbNotes);
                //System.out.println(tbNotePageInfo);
                map=new HashMap<String, Object>();
                map.put("pageinfo",tbNotePageInfo);
                map.put("total",tbNotePageInfo.getTotal());
            }
            return map;
        }

        //设置分页
        PageHelper.startPage(pageNum,pageSize);
        //获取查询结果
        List<TbNote> tbNotes = tbNoteMapper.selectByExample(tbNoteExample);
        //Map<String, Object> map =null;
        //有数据
        if (tbNotes!=null){
            //将分页结果放入
            PageInfo<TbNote> tbNotePageInfo = new PageInfo<>(tbNotes);
            //System.out.println(tbNotePageInfo);
            map=new HashMap<String, Object>();
            map.put("pageinfo",tbNotePageInfo);
            map.put("total",tbNotePageInfo.getTotal());
        }
        return map;
    }

    @Override
    public Map<String, Object> getDateInfo(TbNote tbNote) {
        //设置查询所需的参数
        Integer typeId = tbNote.getId();
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
    public Map<String, Object> getdateAll(String date) {
        HashMap<String, Object> map = new HashMap<>();
        List<TbNote> sell = tbNoteMapper.sell(date);
        map.put("sell",sell);
        return map;
    }

    @Override
    public Map<String, Object> selectTypess(Integer noteId, Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        if (noteId==null){
            List<TbNote> tbNotes = tbNoteMapper.selectByExample(null);
            map.put("list",tbNotes);
        }else {
            TbNote tbNote = tbNoteMapper.selectByPrimaryKey(noteId);
            map.put("list",tbNote);
        }
        return map;
    }

    @Override
    public Map<String, Object> getDateType(TbNote tbNote) {
        //设置查询所需的参数
        Integer user_id = tbNote.getId();
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
