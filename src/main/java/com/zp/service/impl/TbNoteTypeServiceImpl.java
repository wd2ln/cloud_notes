package com.zp.service.impl;

import com.zp.dao.TbNoteMapper;
import com.zp.dao.TbNoteTypeMapper;
import com.zp.entity.TbNote;
import com.zp.entity.TbNoteType;
import com.zp.entity.TbNoteTypeExample;
import com.zp.service.TbNoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
