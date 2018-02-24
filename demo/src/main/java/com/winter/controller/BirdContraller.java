package com.winter.controller;


import com.winter.model.BirdSkin;
import com.winter.model.BirdUser;
import com.winter.serverce.BirdService;
import com.winter.util.WeiXin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bird")
public class BirdContraller {

    @Resource
    private WeiXin weiXin;
    protected static Logger logger = LoggerFactory.getLogger(BirdContraller.class);
    @Resource
    private BirdService birdService;


    private Map map = new HashMap();
    private String SUCESS = "sucess";


    @ResponseBody
    @RequestMapping(value = "/login")
    public BirdUser login(String name, String userID) {

        BirdUser user = birdService.getUser(userID);
        if (user == null) {
            try {
                user = birdService.addUser(userID, name);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        com.winter.util.Logger d = com.winter.util.Logger.getLogger(BirdContraller.class);

        return user;

    }

    @RequestMapping(value = "/update")
    public Map updated(BirdUser user) {

        int i = birdService.update(user);
        map.clear();
        map.put(SUCESS, i);

        return map;
    }

    @RequestMapping(value = "/shopping")
    public Map shopping(BirdSkin point) {

        int i = 0;
        if (point != null) {
            try {
                i = birdService.shopping(point);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        map.clear();
        map.put(SUCESS, i);
        return map;
    }

    @RequestMapping(value = "/ranking")
    public HashMap ranking() {
        HashMap<String, List<BirdUser>> map = new HashMap<>();
        List<BirdUser> ranking = birdService.getRanking();

        try {
            map.put("result", ranking);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @RequestMapping(value = "/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) {
        try {
            weiXin.weixin_notify(request, response, false);
        } catch (Exception e) {
            System.out.println(e);

        }

    }
}
