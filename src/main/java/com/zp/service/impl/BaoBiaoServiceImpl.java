package com.zp.service.impl;

import com.zp.dao.TbNoteMapper;
import com.zp.entity.TbNote;
import com.zp.service.BaoBiaoService;
import com.zp.vo.MonthCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaoBiaoServiceImpl implements BaoBiaoService {
    @Autowired
    private TbNoteMapper tbNoteMapper;
    @Override
    public List<TbNote> selectJW(Integer id) {
        List<TbNote> tbNote=null;
        try {
             tbNote = tbNoteMapper.selectNS(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tbNote;
    }

    @Override
    public Map<String, Object> selectMC(Integer id) {
        List<MonthCountVo> monthCountVos = tbNoteMapper.selectMCS(id);
        List<String> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        for (MonthCountVo monthCountVo : monthCountVos) {
            list.add(monthCountVo.getMonth());
            list1.add(monthCountVo.getCount());
        }
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map.put("monthArray",list);
        map.put("dataArray",list1);
        map1.put("code",0);
        if (map!=null){
            map1.put("data",map);
            map1.put("code",1);
        }

        return map1;
    }
}
