package com.winter.mapper;

import com.winter.model.BirdUser;

public interface BirdUserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(BirdUser record);

    int insertSelective(BirdUser record);

    BirdUser selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(BirdUser record);

    int updateByPrimaryKey(BirdUser record);
}