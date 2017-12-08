package com.winter.mapper;

import com.winter.model.BirdSkin;

import java.util.List;

public interface BirdSkinMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BirdSkin record);

    int insertSelective(BirdSkin record);

    BirdSkin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BirdSkin record);

    int updateByPrimaryKey(BirdSkin record);

    List<BirdSkin> selectByUserID(String userid);

    List<Integer> selectSkinSByUserID(String userid);
}