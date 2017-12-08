package com.winter.serverce;

import com.winter.mapper.CatPointMapper;
import com.winter.mapper.CatUserMapper;
import com.winter.model.CatPoint;
import com.winter.model.CatUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CatService {
    @Resource
    private CatUserMapper catUserMapper;

    @Resource
    private CatPointMapper CatPointMapper;

    public CatUser getUser(String openid) {
        CatUser catUser = catUserMapper.selectByPrimaryKey(openid);
        List<CatPoint> catPoints = CatPointMapper.selectByOpenid(openid);
        if (catUser != null) {
            catUser.setPoints(catPoints);
        }

        return catUser;
    }

    @Transactional
    public CatUser addUser(String openid) {
        CatUser catUser = new CatUser(openid);
        catUserMapper.insert(catUser);
        CatPointMapper.insertAll();
        CatPointMapper.updateForAdd(openid);
        return this.getUser(openid);

    }

    public int updateUser(CatUser user) {
        return catUserMapper.updateByPrimaryKeySelective(user);
    }

    public int updatePoint(CatPoint catPoint) {
        return CatPointMapper.updateByPointID(catPoint);
    }
}
