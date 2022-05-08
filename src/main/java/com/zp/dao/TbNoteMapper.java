package com.zp.dao;

import com.zp.entity.TbDateAndCount;
import com.zp.entity.TbNote;
import com.zp.entity.TbNoteExample;
import com.zp.vo.MonthCountVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbNoteMapper {
    long countByExample(TbNoteExample example);

    int deleteByExample(TbNoteExample example);

    int deleteByPrimaryKey(Integer id);
    //自定义
    List<TbDateAndCount> selectByDateAndCount(Integer typeId);
    //自定义
    List<TbNote> sell(String date);

    int insert(TbNote record);

    int insertSelective(TbNote record);

    List<TbNote> selectByExampleWithBLOBs(TbNoteExample example);

    List<TbNote> selectByExample(TbNoteExample example);
    List<TbNote> selectByExample1(String title);

    TbNote selectByPrimaryKey(Integer id);
    List<TbNote> selectNS(Integer id);

    int updateByExampleSelective(@Param("record") TbNote record, @Param("example") TbNoteExample example);

    int updateByExampleWithBLOBs(@Param("record") TbNote record, @Param("example") TbNoteExample example);

    int updateByExample(@Param("record") TbNote record, @Param("example") TbNoteExample example);

    int updateByPrimaryKeySelective(TbNote record);

    int updateByPrimaryKeyWithBLOBs(TbNote record);

    int updateByPrimaryKey(TbNote record);

    List<MonthCountVo> selectMCS(Integer id);

    List<TbNote> selectbyTypeId(Integer id);
}