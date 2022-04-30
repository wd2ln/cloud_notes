package com.zp.service;

import java.util.Map;

public interface TbNoteTypeService {
    Map<String,Object> searchById(Integer id);

    Map<String,Object> selectType(Integer id);
}
