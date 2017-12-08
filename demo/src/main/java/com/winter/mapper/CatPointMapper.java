package com.winter.mapper;

import com.winter.model.CatPoint;

import java.util.List;

public interface CatPointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CatPoint record);

    int insertSelective(CatPoint record);

    CatPoint selectByPrimaryKey(Integer id);

    List<CatPoint> selectByOpenid(String openid);

    int updateForAdd(String openid);

    int updateByPrimaryKeySelective(CatPoint record);

    int updateByPointID(CatPoint record);

    int updateByPrimaryKey(CatPoint record);

    int insertAll();

}