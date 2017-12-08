package com.winter.mapper;

import com.winter.model.BirdSkin;

public interface BirdSkinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BirdSkin record);

    int insertSelective(BirdSkin record);

    BirdSkin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BirdSkin record);

    int updateByPrimaryKey(BirdSkin record);
}