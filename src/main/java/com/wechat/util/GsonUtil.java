package com.wechat.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rongyaowen
 * on 2018/10/22.
 */
public class GsonUtil {
    private static Gson gson = new Gson();

    /**
     * 转为json串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * json串转为对象
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * 成功json格式
     *
     * @param mess 成功信息
     * @return
     */
    public static String successJson(String mess) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("mess", mess);
        return toJson(map);
    }

    /**
     * 失败json格式
     *
     * @param mess 失败信息
     * @return
     */
    public static String failJson(String mess) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("mess", mess);
        return toJson(map);
    }

}
