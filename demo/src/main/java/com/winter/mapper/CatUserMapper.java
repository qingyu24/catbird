package com.winter.mapper;

import com.winter.model.CatUser;

public interface CatUserMapper {
    int deleteByPrimaryKey(String openid);

    int insert(CatUser record);

    int insertSelective(CatUser record);

    CatUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(CatUser record);

    int updateByPrimaryKey(CatUser record);
}