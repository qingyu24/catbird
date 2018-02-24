package com.winter.serverce;

import com.winter.mapper.BirdSkinMapper;
import com.winter.mapper.BirdUserMapper;
import com.winter.model.BirdSkin;
import com.winter.model.BirdUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service

public class BirdService {
    @Resource
    private BirdSkinMapper birdSkinMapper;

    @Resource
    private BirdUserMapper birdUserMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private int rate = 10;

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
    public BirdUser addUser(String openid, String name) throws java.lang.Exception {
        BirdUser birdUser = new BirdUser();
        birdUser.setuserID(openid);
        birdUser.setName(name);
        birdUserMapper.insertSelective(birdUser);

        return this.getUser(openid);

    }

    public int update(BirdUser user) {

        BirdUser birdUser = birdUserMapper.selectByPrimaryKey(user.getuserID());

        if (user != null && user.getGold() != null && birdUser != null) {
            user.setGold(birdUser.getGold() + user.getGold());
        } else {
            user.setGold(null);
        }
        if (user.getScore() != null) {
            user.setScore(Math.max(user.getScore(), birdUser.getScore()));
        }
        return birdUserMapper.updateByPrimaryKeySelective(user);
    }


    @Transactional
    public int shopping(BirdSkin skin) throws java.lang.Exception {
        List<Integer> integers = birdSkinMapper.selectSkinSByUserID(skin.getUserID());
        if (integers.contains(skin.getSkinID())) return 0;
        int i = birdSkinMapper.insertSelective(skin);
        BirdUser birdUser = birdUserMapper.selectByPrimaryKey(skin.getUserID());
        if (birdUser.getGold() > skin.getPrice()) birdUser.setGold(birdUser.getGold() - skin.getPrice());
        int i1 = birdUserMapper.updateByPrimaryKey(birdUser);
        return (i > 0 && i1 > 0) ? 1 : 0;
    }


    public void updateMoney(String openid, int total_fee) {
        System.out.println("开始执行更新");
        BirdUser birdUser = birdUserMapper.selectByPrimaryKey(openid);
        if (birdUser != null) {
            Integer money = birdUser.getGold();
            birdUser.setGold(money + (int) total_fee * rate);
            birdUserMapper.updateByPrimaryKeySelective(birdUser);
            logger.info("账户为" + openid + "充值成功。充值金额为" + total_fee);
        } else {
            logger.info("账户为" + openid + "充值失败充值金额为" + total_fee);
        }
    }

    public List<BirdUser> getRanking() {

        return birdUserMapper.selectlist();
    }
}
