package com.winter.serverce;

import com.winter.mapper.BirdSkinMapper;
import com.winter.mapper.BirdUserMapper;
import com.winter.model.BirdSkin;
import com.winter.model.BirdUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BirdService  {
    @Resource
    private BirdSkinMapper birdSkinMapper;

    @Resource
    private BirdUserMapper birdUserMapper;

    public BirdUser getUser(String openid) {
        BirdUser birdUser = birdUserMapper.selectByPrimaryKey(openid);
        if (birdUser != null) {
            // List<BirdSkin> birdSkins = birdSkinMapper.selectByuserID(birdUser.getuserID());
            List<Integer> birdSkins = birdSkinMapper.selectSkinSByUserID(birdUser.getuserID());
            birdUser.setSkins(birdSkins);
        }

        return birdUser;
    }

    @Transactional
    public BirdUser addUser(String openid, String name) throws Exception {
        BirdUser birdUser = new BirdUser();
        birdUser.setuserID(openid);
        birdUser.setName(name);
        birdUserMapper.insertSelective(birdUser);

        return this.getUser(openid);

    }

    public int update(BirdUser user) {
        return birdUserMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    public int shopping(BirdSkin skin) throws Exception {
        List<Integer> integers = birdSkinMapper.selectSkinSByUserID(skin.getUserID());
        if (integers.contains(skin.getSkinID())) return 0;
        int i = birdSkinMapper.insertSelective(skin);
        BirdUser birdUser = birdUserMapper.selectByPrimaryKey(skin.getUserID());
        if (birdUser.getGold() > skin.getPrice()) birdUser.setGold(birdUser.getGold() - skin.getPrice());
        int i1 = birdUserMapper.updateByPrimaryKey(birdUser);
        return (i > 0 && i1 > 0) ? 1 : 0;
    }



}
