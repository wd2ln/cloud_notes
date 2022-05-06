package com.zp.service;

import com.zp.entity.TbNote;

import java.util.List;
import java.util.Map;

public interface BaoBiaoService {
    List<TbNote> selectJW(Integer id);

    Map<String,Object> selectMC(Integer id);
}
