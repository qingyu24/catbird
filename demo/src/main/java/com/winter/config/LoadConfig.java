package com.winter.config;

import com.winter.util.XlsReader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by qingyu on 2018/1/18 /15:04
 */
public class LoadConfig {
    static LoadConfig instance;

    public static LoadConfig getInstance() {
        if (instance == null) instance = new LoadConfig();
        return instance;
    }

   public  ArrayList<prizelist> load = new ArrayList<prizelist>(10);
    public  ArrayList<prizelist> adload = new ArrayList<prizelist>(10);
    public void LoadConfig() {
        HashMap<String, ArrayList> map = new HashMap<>();
        map.put(prizelist.class.getSimpleName(), load);
        map.put(Advertisement.class.getSimpleName(), adload);
        XlsReader.getInstance().loaderAll(map);
    }
}
