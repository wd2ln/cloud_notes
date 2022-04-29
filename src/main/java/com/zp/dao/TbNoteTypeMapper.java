package com.zp.dao;

import com.zp.entity.TbDateAndCount;
import com.zp.entity.TbNoteType;
import com.zp.entity.TbNoteTypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbNoteTypeMapper {
    long countByExample(TbNoteTypeExample example);

    int deleteByExample(TbNoteTypeExample example);

    //自定义类型查询
    List<TbDateAndCount> selectByType(Integer user_id);

    int deleteByPrimaryKey(Integer id);

    int insert(TbNoteType record);

    int insertSelective(TbNoteType record);

    List<TbNoteType> selectByExample(TbNoteTypeExample example);

    TbNoteType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbNoteType record, @Param("example") TbNoteTypeExample example);

    int updateByExample(@Param("record") TbNoteType record, @Param("example") TbNoteTypeExample example);

    int updateByPrimaryKeySelective(TbNoteType record);

    int updateByPrimaryKey(TbNoteType record);
}