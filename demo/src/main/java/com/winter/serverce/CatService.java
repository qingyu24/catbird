package com.winter.serverce;

import com.winter.config.LoadConfig;
import com.winter.config.prizelist;
import com.winter.mapper.CatPointMapper;
import com.winter.mapper.CatUserMapper;
import com.winter.model.CatPoint;
import com.winter.model.CatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */

@Service
public class CatService {
    @Resource
    private CatUserMapper catUserMapper;

    @Resource
    private CatPointMapper CatPointMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private double rate = 1.5;//汇率 一分钱等于多少钻石

    public CatUser getUser(String openid) {
        CatUser catUser = catUserMapper.selectByPrimaryKey(openid);
        List<CatPoint> catPoints = CatPointMapper.selectByOpenid(openid);
        if (catUser != null) {
            catUser.setPoints(catPoints);
        }
        return catUser;
    }


    /**
     * @param openid
     * @return
     */
    @Transactional
    public CatUser addUser(String openid) {
        CatUser catUser = new CatUser(openid);
        catUserMapper.insert(catUser);
        CatPointMapper.insertAll();
        CatPointMapper.updateForAdd(openid);
        return this.getUser(openid);

    }

    public int updateUser(CatUser user) {
    /*    CatUser catUser = catUserMapper.selectByPrimaryKey(user.getOpenid());
        if (user.getMoney() - catUser.getMoney() < 100) {*/
        return catUserMapper.updateByPrimaryKeySelective(user);
   /*     } else {
            re0turn 0;
        }*/
    }

    public int updatePoint(CatPoint catPoint) {
        return CatPointMapper.updateByPointID(catPoint);
    }

    public void updateMoney(String openid, Integer total_fee) {
        System.out.println("开始执行更新");
        CatUser catUser = catUserMapper.selectByPrimaryKey(openid);
        if (catUser != null) {
            Integer money = catUser.getMoney();
            catUser.setMoney((int) (money + total_fee * rate));
            catUserMapper.updateByPrimaryKeySelective(catUser);
            logger.info("账户为" + openid + "充值成功。充值金额为" + total_fee);
        } else {
            logger.info("账户为" + openid + "充值失败充值金额为" + total_fee);
        }
    }

    @Transactional
    public boolean lottery(String openid, Map map) {

        CatUser catUser = catUserMapper.selectByPrimaryKey(openid);
        logger.info("抽奖前"+catUser.toString());
        if (catUser.getMoney() < 500) {
            return false;
        }
        //抽奖扣除
        catUser.setMoney(catUser.getMoney() - 500);
        int count = (int) (Math.random() * 8);

        ArrayList<prizelist> load = LoadConfig.getInstance().load;
        prizelist prize = load.get(count);
        logger.info("抽到的奖品"+prize.toString());
        if (prize != null) {
            if (prize.money != 0) {
                catUser.setMoney(catUser.getMoney() + prize.money);
            } else {
                switch (prize.prop) {
                    case 1:
                        catUser.setProps1(catUser.getProps1() + prize.count);
                        break;
                    case 2:
                        catUser.setProps2(catUser.getProps2() + prize.count);
                        break;
                    case 3:
                        catUser.setProps3(catUser.getProps3() + prize.count);
                        break;
                }
            }
            catUserMapper.updateByPrimaryKeySelective(catUser);
            logger.info("更新了..." + catUser.toString());
            map.put("prize", prize);
        }
        ArrayList<CatPoint> objects = new ArrayList<CatPoint>();
        catUser.setLevelGameData(objects);
        return true;
    }
}
