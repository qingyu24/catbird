package com.winter.controller;


import com.winter.model.CatPoint;
import com.winter.model.CatUser;
import com.winter.serverce.CatService;
import com.winter.util.WeiXin;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
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
public class CatContraller {


    @Resource
    private CatService catService;

    private Map map = new HashMap();
    private String SUCESS = "sucess";

    private WeiXin weiXin = new WeiXin();

    @ResponseBody
    @RequestMapping(value = "/login")
    public CatUser login(String method, String openid) {
        CatUser user = catService.getUser(openid);
        if (user == null) {
            user = catService.addUser(openid);
log
        }
        user.setSucess(1);
        return user;

    }

    @RequestMapping(value = "/update")
    public Map updated(CatUser user) {

        int i = catService.updateUser(user);
        map.clear();
        map.put(SUCESS, i);
        return map;
    }

    @RequestMapping(value = "/passPoint")
    public Map passPoint(CatPoint point) {
        int i = catService.updatePoint(point);
        map.clear();
        map.put(SUCESS, i);
        return map;
    }

    @RequestMapping(value = "/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) {
        try {
            weiXin.weixin_notify(request, response);
        } catch (Exception e) {
            System.out.println("______________________");

        }

    }
}
