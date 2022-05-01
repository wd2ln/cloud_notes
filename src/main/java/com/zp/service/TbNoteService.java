package com.zp.service;

import com.zp.entity.TbNote;

import java.util.Map;

public interface TbNoteService {

    Map<String,Object> getDates(TbNote tbNote, Integer pageNum, Integer pageSize);

    Map<String,Object> getDateInfo(TbNote tbNote);

    Map<String,Object> getDateType(TbNote tbNote);

    Map<String, Object> selectTypess(Integer noteId, Integer id);

    Map<String, Object> getdateAll(String date);
}
