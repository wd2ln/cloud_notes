package com.zp.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface TbNoteTypeService {
    Map<String,Object> searchById(Integer id);

    Map<String,Object> selectType(Integer id);

    Boolean updates(String typeName,Integer id);

    Boolean del(Integer id);

    Integer add(String typeName, HttpServletRequest request);
}
