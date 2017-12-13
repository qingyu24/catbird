package com.winter.serverce;

import com.winter.mapper.CatPointMapper;
import com.winter.mapper.CatUserMapper;
import com.winter.model.CatPoint;
import com.winter.model.CatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    private int rate = 10;//汇率 一分钱等于多少钻石

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
        return catUserMapper.updateByPrimaryKeySelective(user);
    }

    public int updatePoint(CatPoint catPoint) {
        return CatPointMapper.updateByPointID(catPoint);
    }

    public void updateMoney(String openid, Integer total_fee) {
        System.out.println("开始执行更新");
        CatUser catUser = catUserMapper.selectByPrimaryKey(openid);
        if (catUser != null) {
            Integer money = catUser.getMoney();
            catUser.setMoney(money + (int) total_fee * rate);
            catUserMapper.updateByPrimaryKeySelective(catUser);
            logger.info("账户为" + openid + "充值成功。充值金额为" + total_fee);
        } else {
            logger.info("账户为" + openid + "充值失败充值金额为" + total_fee);
        }
    }


}
