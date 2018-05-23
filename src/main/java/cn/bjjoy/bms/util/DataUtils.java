package cn.bjjoy.bms.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by bjjoy on 2017/11/02.
 */
public class DataUtils {
    private static final Logger logger = LoggerFactory.getLogger(DataUtils.class);

    /**
     * 转化为List<T>
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getDataArray(Object data, Class<T> clazz) {
        String jsonString = JSONObject.toJSONString(data);
        return JSONObject.parseArray(jsonString, clazz);
    }

    /**
     * 转化为T
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getData(Object data, Class<T> clazz) {
        String jsonString = JSONObject.toJSONString(data);
        return JSONObject.parseObject(jsonString, clazz);
    }
}
