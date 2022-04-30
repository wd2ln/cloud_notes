package com.zp.service.impl;

import com.zp.dao.TbNoteMapper;
import com.zp.service.TbNoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TbNoteTypeServiceImpl implements TbNoteTypeService {
    @Autowired
    private TbNoteMapper tbNoteMapper;
    @Override
    public Map<String,Object> searchById(Integer id) {
        TbNoteServiceImpl tbNoteService = new TbNoteServiceImpl();
        //tbNoteService.getDates()
        return null;
    }
}
