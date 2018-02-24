package com.winter.controller;


import com.winter.config.LoadConfig;
import com.winter.model.CatPoint;
import com.winter.model.CatUser;
import com.winter.serverce.CatService;
import com.winter.util.WeiXin;
import groovy.util.logging.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/cat")
public class CatContraller {


    @Resource
    private CatService catService;


    private String SUCESS = "sucess";
    @Resource
    private WeiXin weiXin;

    @ResponseBody
    @RequestMapping(value = "/login")
    public CatUser login(String method, String openid) {
        CatUser user = catService.getUser(openid);
        if (user == null) {
            user = catService.addUser(openid);
        }
        user.setSucess(1);
        return user;
    }

    @RequestMapping(value = "/update")
    public Map updated(CatUser user) {
        Map map = new HashMap();
        int i = catService.updateUser(user);
        map.clear();
        map.put(SUCESS, i);
        return map;
    }

    @RequestMapping(value = "/passPoint")
    public Map passPoint(CatPoint point) {
        Map map = new HashMap();
        int i = catService.updatePoint(point);
        map.clear();
        map.put(SUCESS, i);
        return map;
    }

    @RequestMapping(value = "/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) {
        try {
            weiXin.weixin_notify(request, response, true);
        } catch (Exception e) {
            org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
            logger.error("支付异常", e);

        }

    }

    //抽奖
    @RequestMapping(value = "/lottery")
    public Map lottery(String openid) {

        Map map = new HashMap();
        boolean lottery = catService.lottery(openid, map);
        map.put("sucess", lottery ? 1 : 0);
        return map;
    }


    //抽奖所有配置
    @RequestMapping(value = "/config")
    public HashMap<Object, Object> config(String openid) {

        HashMap<Object, Object> map = new HashMap<>();
        map.put("list", LoadConfig.getInstance().load);
        return map;
    }


    @RequestMapping(value = "/advertise")
    public HashMap<Object, Object> advertise(String openid) {

        HashMap<Object, Object> map = new HashMap<>();
        map.put("list", LoadConfig.getInstance().adload);
        return map;
    }
}
