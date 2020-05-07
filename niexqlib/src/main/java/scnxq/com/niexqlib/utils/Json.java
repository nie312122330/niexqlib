/**
 * 2014-3-3 下午3:55:51 Created By niexiaoqiang
 */

package scnxq.com.niexqlib.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一种使用方式：
 * List<String> list = new ArrayList<String>();
 * list.add("fastjson1");
 * list.add("fastjson2");
 * String jsonString = JSON.toJSONString(list);
 * List<String> list2 = JSON.parseObject(jsonString,new TypeReference<List<String>>(){});
 * <p/>
 * 第二种使用方式：
 * Map<String,Object> map = new HashMap<String,Object>();
 * map.put("key1", "value1");
 * Map<String,Object> map2 = new HashMap<String,Object>();
 * map2.put("key1", 1);
 * List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
 * list.add(map);
 * list.add(map2);
 * String jsonString = JSON.toJSONString(list);
 * List<Map<String,Object>> list2 = JSON.parseObject(jsonString,new TypeReference<List<Map<String,Object>>>(){});
 */
public class Json {


    /**
     * @param str
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T str2Obj(String str, TypeReference<T> type) {
        return JSON.parseObject(str, type, Feature.AutoCloseSource);
    }

    /**
     * 从字符串转位对应的对象
     *
     * @param str
     * @param beanObj
     * @return
     */
    public static <T> T str2Obj(String str, Class<T> beanObj) {
        try {
            return JSON.parseObject(str, beanObj);
        } catch (Exception e) {
            LogUtils.error(e);
            return null;
        }
    }

    /**
     * 从字符串转位对应的对象
     *
     * @param <T>
     * @param str
     * @param beanObj
     * @return
     */
    public static <T> List<T> str2List(String str, Class<T> beanObj) {
        try {
            return JSON.parseArray(str, beanObj);
        } catch (Exception e) {
            LogUtils.error(e);
            return new ArrayList<>(0);
        }
    }

    /**
     * 从对象转为String
     *
     * @param object
     * @return
     */
    public static String obj2Str(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            LogUtils.error(e);
            return null;
        }
    }

}
